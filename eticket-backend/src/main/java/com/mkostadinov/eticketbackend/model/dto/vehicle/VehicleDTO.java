package com.mkostadinov.eticketbackend.model.dto.vehicle;

import com.google.gson.annotations.Expose;
import com.mkostadinov.eticketbackend.model.entity.Ticket;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class VehicleDTO {

    @Expose
    private String id;

    @Expose
    private String registrationNumber;

    @Expose
    private LocalDateTime addedOn;

    @Expose
    private boolean blocked;

    private String ownerDriverLicenseId;


    public VehicleDTO() {
    }


    public String getId() {
        return id;
    }

    public VehicleDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleDTO setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public VehicleDTO setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public VehicleDTO setBlocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public String getOwnerDriverLicenseId() {
        return ownerDriverLicenseId;
    }

    public VehicleDTO setOwnerDriverLicenseId(String ownerDriverLicenseId) {
        this.ownerDriverLicenseId = ownerDriverLicenseId;
        return this;
    }
}
