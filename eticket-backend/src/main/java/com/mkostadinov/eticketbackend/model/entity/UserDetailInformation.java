package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_details_information")
public class UserDetailInformation extends BaseEntity {


    private String userId;
    private String description;
    private String imageUrl;

    public UserDetailInformation() {
    }

    @Column(name = "user_id", unique = true, nullable = false)
    public String getUserId() {
        return userId;
    }

    public UserDetailInformation setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public UserDetailInformation setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public UserDetailInformation setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
