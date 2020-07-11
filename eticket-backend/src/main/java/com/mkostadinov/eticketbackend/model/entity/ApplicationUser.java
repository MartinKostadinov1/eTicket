package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class ApplicationUser extends BaseEntity {

    private String eticketId;
    private String password;
    private UserDetailInformation detailedInformation;
    private LocalDateTime addedOn;
    private LocalDateTime lastLogin;
    private boolean isLocked;
    private boolean isBlocked;

    public ApplicationUser() {
    }

    @Column(name = "eticket_id", nullable = false, unique = true)
    public String getEticketId() {
        return eticketId;
    }

    public ApplicationUser setEticketId(String eticketId) {
        this.eticketId = eticketId;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public ApplicationUser setPassword(String password) {
        this.password = password;
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
