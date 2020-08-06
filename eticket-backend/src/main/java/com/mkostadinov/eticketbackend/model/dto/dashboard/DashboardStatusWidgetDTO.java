package com.mkostadinov.eticketbackend.model.dto.dashboard;

import com.google.gson.annotations.Expose;

public class DashboardStatusWidgetDTO {

    @Expose
    private int monthlyTrips;

    @Expose
    private int previousMonthTrips;

    @Expose
    private int unpaidTickets;

    @Expose
    private int allPaidTickets;

    @Expose
    private int vehicleCount;

    public DashboardStatusWidgetDTO() {
    }

    public DashboardStatusWidgetDTO(int monthlyTrips, int unpaidTickets, int allPaidTickets, int vehicleCount) {
        this.monthlyTrips = monthlyTrips;
        this.unpaidTickets = unpaidTickets;
        this.allPaidTickets = allPaidTickets;
        this.vehicleCount = vehicleCount;
    }

    public int getMonthlyTrips() {
        return monthlyTrips;
    }

    public DashboardStatusWidgetDTO setMonthlyTrips(int monthlyTrips) {
        this.monthlyTrips = monthlyTrips;
        return this;
    }

    public int getUnpaidTickets() {
        return unpaidTickets;
    }

    public DashboardStatusWidgetDTO setUnpaidTickets(int unpaidTickets) {
        this.unpaidTickets = unpaidTickets;
        return this;
    }

    public int getAllPaidTickets() {
        return allPaidTickets;
    }

    public DashboardStatusWidgetDTO setAllPaidTickets(int allPaidTickets) {
        this.allPaidTickets = allPaidTickets;
        return this;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public DashboardStatusWidgetDTO setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
        return this;
    }

    public int getPreviousMonthTrips() {
        return previousMonthTrips;
    }

    public DashboardStatusWidgetDTO setPreviousMonthTrips(int previousMonthTrips) {
        this.previousMonthTrips = previousMonthTrips;
        return this;
    }
}
