package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class AuthProviderUserDTO {
    @JsonProperty(value = "user_id")
    @Expose
    private String authId;

    @JsonProperty(value = "created_at")
    @Expose
    private LocalDateTime createdAt;

    @JsonProperty(value = "last_login")
    @Expose
    private LocalDateTime lastLogin;

    @JsonProperty(value = "updated_at")
    @Expose
    private LocalDateTime updatedAt;

    @JsonProperty(value = "last_ip")
    @Expose
    private String lastIp;

    @JsonProperty(value = "logins_count")
    @Expose
    private int loginsCount;

    @JsonProperty(value = "nickname")
    @Expose
    private String username;

    @JsonProperty(value = "picture")
    @Expose
    private String picture;

    @JsonProperty(value = "email")
    @Expose
    private String email;


    public AuthProviderUserDTO() {
    }

    public String getAuthId() {
        return authId;
    }

    public AuthProviderUserDTO setAuthId(String authId) {
        this.authId = authId;
        return this;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public AuthProviderUserDTO setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public AuthProviderUserDTO setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public AuthProviderUserDTO setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getLastIp() {
        return lastIp;
    }

    public AuthProviderUserDTO setLastIp(String lastIp) {
        this.lastIp = lastIp;
        return this;
    }

    public int getLoginsCount() {
        return loginsCount;
    }

    public AuthProviderUserDTO setLoginsCount(int loginsCount) {
        this.loginsCount = loginsCount;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AuthProviderUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public AuthProviderUserDTO setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AuthProviderUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
