package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class AuthProviderUserUpdateDTO {

    @Expose
    @JsonProperty("nickname")
    private String username;


    public AuthProviderUserUpdateDTO() {
    }

    public String getUsername() {
        return username;
    }

    public AuthProviderUserUpdateDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
