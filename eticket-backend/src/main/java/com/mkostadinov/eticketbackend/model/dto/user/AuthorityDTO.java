package com.mkostadinov.eticketbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class AuthorityDTO {

    @JsonProperty("authorityId")
    @Expose
    private String id;

    @JsonProperty("authorityName")
    @Expose
    private String authorityName;

    public AuthorityDTO() {
    }

    public String getId() {
        return id;
    }

    public AuthorityDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public AuthorityDTO setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }
}
