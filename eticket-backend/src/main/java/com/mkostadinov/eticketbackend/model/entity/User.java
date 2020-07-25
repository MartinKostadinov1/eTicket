package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class ApplicationUser extends BaseEntity {

    private String authId;
    private UserDetailInformation detailedInformation;
    private LocalDateTime addedOn;
    private LocalDateTime lastLogin;
    private boolean isLocked;
    private boolean isBlocked;

    public ApplicationUser() {
    }

    @Column(name = "auth_id", nullable = false, unique = true)
    public String getAuthId() {
        return authId;
    }

    public ApplicationUser setAuthId(String authId) {
        this.authId = authId;
        return this;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_detailed_information_id", referencedColumnName = "user_id", nullable = false)
    public UserDetailInformation getDetailedInformation() {
        return detailedInformation;
    }

    public ApplicationUser setDetailedInformation(UserDetailInformation detailedInformation) {
        this.detailedInformation = detailedInformation;
        return this;
    }

    @Column(name = "added_on", nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public ApplicationUser setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @Column(name = "last_login", nullable = false)
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public ApplicationUser setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    @Column(name = "is_locked")
    public boolean isLocked() {
        return isLocked;
    }

    public ApplicationUser setLocked(boolean locked) {
        isLocked = locked;
        return this;
    }

    @Column(name = "is_blocked")
    public boolean isBlocked() {
        return isBlocked;
    }

    public ApplicationUser setBlocked(boolean blocked) {
        isBlocked = blocked;
        return this;
    }
}
