package com.mkostadinov.eticketbackend.model.dto.vehicle;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class VehicleCreationDTO {

    private String registrationNumber;
    private String ownerDrivingLicenseId;

    public VehicleCreationDTO() {
    }

    @NotBlank
    @Length(min = 8)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleCreationDTO setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    @NotBlank
    @Length(min = 10, max = 10)
    public String getOwnerDrivingLicenseId() {
        return ownerDrivingLicenseId;
    }

    public VehicleCreationDTO setOwnerDrivingLicenseId(String ownerDrivingLicenseId) {
        this.ownerDrivingLicenseId = ownerDrivingLicenseId;
        return this;
    }
}
