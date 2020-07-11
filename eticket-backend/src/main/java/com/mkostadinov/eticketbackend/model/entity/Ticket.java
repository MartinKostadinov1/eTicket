package com.mkostadinov.eticketbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    private String locationName;
    private String locationCoordinates;
    private LocalDateTime createdOn;
    private LocalDateTime paidOn;
    private boolean isPaid;
    private boolean isDeleted;
    private BigDecimal amount;
    private String ticketType;
    private String description;
    private String vehicleRegistrationNumber;

    public Ticket() {
    }

    @Column(name = "location_name", nullable = false)
    public String getLocationName() {
        return locationName;
    }

    public Ticket setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    @Column(name = "location_coordinates", nullable = false)
    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public Ticket setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
        return this;
    }

    @Column(name = "created_on", nullable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Ticket setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Column(name = "paid_on")
    public LocalDateTime getPaidOn() {
        return paidOn;
    }

    public Ticket setPaidOn(LocalDateTime paidOn) {
        this.paidOn = paidOn;
        return this;
    }


    @Column(name = "is_paid")
    public boolean isPaid() {
        return isPaid;
    }

    public Ticket setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public Ticket setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Column(name = "amount", nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public Ticket setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Column(name = "ticket_type", nullable = false)
    public String getTicketType() {
        return ticketType;
    }

    public Ticket setTicketType(String ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Ticket setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "vehicle_registration_number", nullable = false)
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public Ticket setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        return this;
    }
}
