package com.mkostadinov.eticketbackend.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mkostadinov.eticketbackend.model.dto.user.AuthProviderUserDTO;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class AuthProviderUserGsonSerializer implements JsonSerializer<AuthProviderUserDTO> {

    private DateTimeFormatter frm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(AuthProviderUserDTO user, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("userId", user.getAuthId());

        jsonObject.addProperty("createdAt", user.getCreatedAt() != null ?
                frm.format(user.getCreatedAt()) : null);

        jsonObject.addProperty("lstLogin", user.getLastLogin() != null ?
                frm.format(user.getLastLogin()) : null);

        jsonObject.addProperty("updatedAt", user.getUpdatedAt() != null ?
                frm.format(user.getUpdatedAt()) : null);

        jsonObject.addProperty("lastIp", user.getLastIp());

        jsonObject.addProperty("loginsCount", user.getLoginsCount());

        jsonObject.addProperty("username", user.getUsername());

        jsonObject.addProperty("picture", user.getPicture());

        jsonObject.addProperty("email", user.getEmail());


        return jsonObject;


    }
}
