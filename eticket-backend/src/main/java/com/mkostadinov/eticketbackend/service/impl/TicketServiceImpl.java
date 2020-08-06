package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.model.dto.ticket.TicketCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import com.mkostadinov.eticketbackend.model.entity.Ticket;
import com.mkostadinov.eticketbackend.model.entity.Vehicle;
import com.mkostadinov.eticketbackend.repository.TicketRepository;
import com.mkostadinov.eticketbackend.service.TicketService;
import com.mkostadinov.eticketbackend.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, VehicleService vehicleService, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDTO createTicket(TicketCreationDTO newTicket) {
        VehicleDTO vehicle = this.vehicleService.findByRegistrationNumber(newTicket.getVehicleRegistrationNumber());

        if(vehicle == null) {
            throw new IllegalArgumentException("Invalid vehicle registration number provided for ticket");
        }

        Ticket ticket = this.modelMapper.map(newTicket, Ticket.class);

        ticket.setDeleted(false).setPaid(false);

        this.ticketRepository.saveAndFlush(ticket);

        TicketDTO ticketDTO = this.modelMapper.map(ticket, TicketDTO.class);
        vehicle.getTickets().add(ticketDTO);

        this.vehicleService.saveVehicle(vehicle);

        return ticketDTO;
    }

    @Override
    public TicketDTO deleteTicket(String id) {
        Ticket ticket = this.ticketRepository.findById(id).orElse(null);
        if(ticket == null) {
            throw new IllegalArgumentException("Invalid ticket id!");
        }

        if(ticket.isPaid()) {
            throw new IllegalArgumentException("Ticket is already paid.");
        }

        if(ticket.isDeleted()) {
            throw new IllegalArgumentException("Ticket is already deleted.");
        }
        ticket.setDeleted(true);

        this.ticketRepository.saveAndFlush(ticket);


        return this.modelMapper.map(ticket, TicketDTO.class);
    }
}
