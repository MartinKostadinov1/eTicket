package com.mkostadinov.eticketbackend.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.mkostadinov.eticketbackend.service.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudServiceImpl implements CloudService {
    private static final String RAW_TYPE_POSTFIX = "&raw=1";
    private final Logger log = LoggerFactory.getLogger(CloudServiceImpl.class);

    private final DbxClientV2 client;

    @Autowired
    public CloudServiceImpl(@Value("${app.dropbox.access.token}") String ACCESS_TOKEN) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/eticket").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    @Override
    public Map<String, String> uploadFileAndGetParams(MultipartFile file, String type) {
        UUID uuid = UUID.randomUUID();

        String imagePath = "/" + type + "." + uuid + file.getOriginalFilename().replaceAll("^.*\\.", ".");
        uploadFile(file, imagePath);
        log.info("File upload successful.");

        SharedLinkMetadata sharedLinkWithSettings = createSharedLinkFromPath(imagePath);
        return new HashMap<>() {{
            put("url", sharedLinkWithSettings.getUrl() + RAW_TYPE_POSTFIX);
            put("contentType", file.getContentType());
        }};
    }

    @Override
    public Map<String, String> updateFile(MultipartFile newFile, String oldFileId, String type) {
        removeFile(oldFileId);
        return uploadFileAndGetParams(newFile, type);
    }

    @Override
    public boolean removeFile(String fileId) {
        SharedLinkMetadata sharedLinkMetadata;
        try {
            sharedLinkMetadata = getSharedLinkMetadata(fileId);

            if (sharedLinkMetadata == null) return false;
            client.files().deleteV2(sharedLinkMetadata.getPathLower());
            return true;
        } catch (DbxException | NullPointerException e) {
            log.error("File deletion failed.");
        }
        return false;
    }

    private void uploadFile(MultipartFile file, String imagePath) {
        InputStream in;
        try {
            in = file.getInputStream();
            client.files().uploadBuilder(imagePath).uploadAndFinish(in);
        } catch (IOException | DbxException e) {
            log.error("File upload failed.");
        }
    }

    private SharedLinkMetadata createSharedLinkFromPath(String path) {
        try {
            return client.sharing().createSharedLinkWithSettings(path);
        } catch (DbxException e) {
            log.error("Creating shared link failed.");
        }
        return null;
    }

    private SharedLinkMetadata getSharedLinkMetadata(String url) {
        try {
            return client.sharing().getSharedLinkMetadata(url);
        } catch (DbxException | NullPointerException e) {
            log.error("Retrieving shared link failed.");
        }
        return null;
    }
}
