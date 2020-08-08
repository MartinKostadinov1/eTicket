package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.exception.payment.PaymentUnableToProceedException;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import com.mkostadinov.eticketbackend.model.entity.Ticket;
import com.mkostadinov.eticketbackend.repository.TicketRepository;
import com.mkostadinov.eticketbackend.service.EmailService;
import com.mkostadinov.eticketbackend.service.TicketService;
import com.mkostadinov.eticketbackend.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, VehicleService vehicleService, ModelMapper modelMapper, EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
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

        this.emailService.sendNewTicketEmail(vehicle, ticketDTO.getDescription(), ticketDTO.getCreatedOn());

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

    @Override
    public TicketDTO findById(String ticketId) {
        return this.ticketRepository.findById(ticketId).map(t -> this.modelMapper.map(t, TicketDTO.class)).orElse(null);
    }

    @Override
    public void payTicket(TicketDTO ticketDTO) {
        if(ticketDTO == null) { throw new PaymentUnableToProceedException("Ticket doesn't exist"); }

        ticketDTO.setPaidOn(LocalDateTime.now());
        ticketDTO.setPaid(true);

        this.ticketRepository.saveAndFlush(this.modelMapper.map(ticketDTO, Ticket.class));
    }
}
