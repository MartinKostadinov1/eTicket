package com.mkostadinov.eticketbackend.model.dto.auth0;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public AccessTokenDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public AccessTokenDTO setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }
}
