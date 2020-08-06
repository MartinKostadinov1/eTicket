package com.mkostadinov.eticketbackend.model.dto.ticket;

import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketCreationDTO {

    @Expose
    private String locationName;

    @Expose
    private String locationCoordinates;

    @Expose
    private LocalDateTime createdOn;

    @Expose
    private BigDecimal amount;

    @Expose
    private String ticketType;

    @Expose
    private String description;

    @Expose
    private String vehicleRegistrationNumber;


    public TicketCreationDTO() {
    }

    public String getLocationName() {
        return locationName;
    }

    public TicketCreationDTO setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public TicketCreationDTO setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TicketCreationDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public TicketCreationDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getTicketType() {
        return ticketType;
    }

    public TicketCreationDTO setTicketType(String ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TicketCreationDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public TicketCreationDTO setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        return this;
    }
}
