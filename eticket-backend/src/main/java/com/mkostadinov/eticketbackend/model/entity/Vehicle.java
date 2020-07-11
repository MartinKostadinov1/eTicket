package com.mkostadinov.eticketbackend.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    private String registrationNumber;
    private LocalDateTime addedOn;
    private boolean blocked;

    public Vehicle() {
    }

    @Column(name = "registration_number", unique = true, nullable = false)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Vehicle setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    @Column(name = "added_on", nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public Vehicle setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @Column(name = "is_blocked")
    public boolean isBlocked() {
        return blocked;
    }

    public Vehicle setBlocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }
}
