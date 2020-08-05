package com.mkostadinov.eticketbackend.model.dto.vehicle;

public class VehiclesApiResponseDTO {

    private String id;
    private String vehicleRegistrationNumber;
    private String ownerDriverLicenseId;

    public VehiclesApiResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public VehiclesApiResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public VehiclesApiResponseDTO setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        return this;
    }

    public String getOwnerDriverLicenseId() {
        return ownerDriverLicenseId;
    }

    public VehiclesApiResponseDTO setOwnerDriverLicenseId(String ownerDriverLicenseId) {
        this.ownerDriverLicenseId = ownerDriverLicenseId;
        return this;
    }
}
