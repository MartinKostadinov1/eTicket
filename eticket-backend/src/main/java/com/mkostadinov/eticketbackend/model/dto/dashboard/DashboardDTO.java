package com.mkostadinov.eticketbackend.model.dto.dashboard;

import com.google.gson.annotations.Expose;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;

import java.util.Set;

public class DashboardDTO {

    @Expose
    private String driverLicenseId;

    @Expose
    private String authProviderId;

    @Expose
    private String email;

    @Expose
    private Set<VehicleDTO> vehicles;


    public DashboardDTO() {
    }

    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public DashboardDTO setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
        return this;
    }

    public String getAuthProviderId() {
        return authProviderId;
    }

    public DashboardDTO setAuthProviderId(String authProviderId) {
        this.authProviderId = authProviderId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DashboardDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public DashboardDTO setVehicles(Set<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
        return this;
    }
}
