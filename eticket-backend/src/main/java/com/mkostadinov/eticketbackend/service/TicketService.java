package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.model.dto.ticket.TicketCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;

public interface TicketService {

    TicketDTO createTicket(TicketCreationDTO newTicket);
    TicketDTO deleteTicket(String id);
}
