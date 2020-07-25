package com.mkostadinov.eticketbackend.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class ApplicationAuthority extends BaseEntity {

    private String authorityName;

    public ApplicationAuthority() {
    }

    @Column(name = "authority_name", nullable = false, unique = true)
    public String getAuthorityName() {
        return authorityName;
    }

    public ApplicationAuthority setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }
}
