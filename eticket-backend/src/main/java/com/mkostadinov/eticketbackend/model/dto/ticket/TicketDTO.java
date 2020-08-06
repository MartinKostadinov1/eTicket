package com.mkostadinov.eticketbackend.model.dto.ticket;

import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDTO {

    @Expose
    private String id;

    @Expose
    private String locationName;

    @Expose
    private String locationCoordinates;

    @Expose
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @Expose
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paidOn;

    @Expose
    private boolean isPaid;

    @Expose
    private boolean isDeleted;

    @Expose
    private BigDecimal amount;

    @Expose
    private String ticketType;

    @Expose
    private String description;

    @Expose
    private String vehicleRegistrationNumber;

    public TicketDTO() {
    }

    public String getId() {
        return id;
    }

    public TicketDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public TicketDTO setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public TicketDTO setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TicketDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getPaidOn() {
        return paidOn;
    }

    public TicketDTO setPaidOn(LocalDateTime paidOn) {
        this.paidOn = paidOn;
        return this;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public TicketDTO setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public TicketDTO setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TicketDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getTicketType() {
        return ticketType;
    }

    public TicketDTO setTicketType(String ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TicketDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public TicketDTO setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        return this;
    }
}
