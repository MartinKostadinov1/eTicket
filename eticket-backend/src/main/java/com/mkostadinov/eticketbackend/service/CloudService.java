package com.mkostadinov.eticketbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudService {

    Map<String, String> uploadFileAndGetParams(MultipartFile file, String type);

    Map<String, String> updateFile(MultipartFile newFile, String oldFileId, String type);

    boolean removeFile(String fileId);

}
