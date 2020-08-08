package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.BaseTest;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.model.entity.Ticket;
import com.mkostadinov.eticketbackend.repository.TicketRepository;
import com.mkostadinov.eticketbackend.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class TicketServiceTest extends BaseTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Ticket ticketToReturn;
    private Ticket ticketToPay;

    @Override
    protected void beforeEach() {
        super.beforeEach();


        this.ticketToReturn = new Ticket() {{
            setPaid(false);
            setAmount(BigDecimal.ONE);
            setCreatedOn(LocalDateTime.now());
            setDescription("Test ticket");
            setLocationCoordinates("1, 1");
            setLocationName("Sofia");
            setTicketType("TEST");
            setVehicleRegistrationNumber("CA 1234 CA");
        }};

        this.ticketToPay = new Ticket() {{
            setId("1");
            setPaid(true);
            setAmount(BigDecimal.ONE);
            setCreatedOn(LocalDateTime.now());
            setDescription("Test ticket");
            setLocationCoordinates("1, 1");
            setLocationName("Sofia");
            setTicketType("TEST");
            setVehicleRegistrationNumber("CA 1234 CA");
        }};


    }

    @Test
    public void testFindTicketById() {

        Mockito.when(this.ticketRepository.findById("1"))
                .thenReturn(Optional.of(this.ticketToReturn));
        Assertions.assertEquals(this.ticketToReturn.getDescription(), this.ticketService.findById("1").getDescription());
    }

    @Test
    public void testPayTicket() {

        Mockito.when(this.ticketRepository.saveAndFlush(this.ticketToPay))
                .thenReturn(this.ticketToPay);


        TicketDTO payedTicket = this.ticketService.payTicket(this.modelMapper.map(this.ticketToPay, TicketDTO.class));
        Assertions.assertEquals(this.ticketToReturn.getDescription(), payedTicket.getDescription());

        Assertions.assertTrue(payedTicket.isPaid());
    }
}
