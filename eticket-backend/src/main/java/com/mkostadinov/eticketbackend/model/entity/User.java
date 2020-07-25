package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String authId;

    public User() {
    }

    @Column(name = "auth_id", nullable = false, unique = true)
    public String getAuthId() {
        return authId;
    }

    public User setAuthId(String authId) {
        this.authId = authId;
        return this;
    }
}
