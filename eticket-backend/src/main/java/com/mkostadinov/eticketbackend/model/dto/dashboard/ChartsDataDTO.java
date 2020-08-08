package com.mkostadinov.eticketbackend.model.dto.dashboard;

import com.google.gson.annotations.Expose;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChartsDataDTO {

    @Expose
    private Map<String, String> expenses;

    @Expose
    private Map<String, String> tickets;

    public ChartsDataDTO() {
        this.expenses = new LinkedHashMap<>();
        this.tickets = new LinkedHashMap<>();
    }

    public Map<String, String> getExpenses() {
        return expenses;
    }

    public ChartsDataDTO setExpenses(Map<String, String> expenses) {
        this.expenses = expenses;
        return this;
    }

    public Map<String, String> getTickets() {
        return tickets;
    }

    public ChartsDataDTO setTickets(Map<String, String> tickets) {
        this.tickets = tickets;
        return this;
    }
}
