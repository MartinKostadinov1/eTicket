package com.mkostadinov.eticketbackend.model.dto.vehicle;

public class VehicleCreationDTO {

    private String registrationNumber;
    private String ownerDrivingLicenseId;

    public VehicleCreationDTO() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleCreationDTO setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public String getOwnerDrivingLicenseId() {
        return ownerDrivingLicenseId;
    }

    public VehicleCreationDTO setOwnerDrivingLicenseId(String ownerDrivingLicenseId) {
        this.ownerDrivingLicenseId = ownerDrivingLicenseId;
        return this;
    }
}
