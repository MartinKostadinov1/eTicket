package com.mkostadinov.eticketbackend.web.api.controllers;

import com.mkostadinov.eticketbackend.model.dto.ticket.TicketCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketCreationDTO ticket) {
        return ResponseEntity.ok(this.ticketService.createTicket(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TicketDTO> deleteTicketByiD(@PathVariable String id) {
        return ResponseEntity.ok(this.ticketService.deleteTicket(id));
    }


}
