package com.mkostadinov.eticketbackend.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UserServiceModel {

    @JsonProperty(value = "user_id")
    private String authId;

    @JsonProperty(value = "added_on")
    private LocalDateTime addedOn;

    @JsonProperty(value = "last_login")
    private LocalDateTime lastLogin;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "last_ip")
    private String lastIp;

    @JsonProperty(value = "logins_count")
    private int loginsCount;


    public String getAuthId() {
        return authId;
    }

    public UserServiceModel setAuthId(String authId) {
        this.authId = authId;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public UserServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public UserServiceModel setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserServiceModel setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getLastIp() {
        return lastIp;
    }

    public UserServiceModel setLastIp(String lastIp) {
        this.lastIp = lastIp;
        return this;
    }

    public int getLoginsCount() {
        return loginsCount;
    }

    public UserServiceModel setLoginsCount(int loginsCount) {
        this.loginsCount = loginsCount;
        return this;
    }
}
