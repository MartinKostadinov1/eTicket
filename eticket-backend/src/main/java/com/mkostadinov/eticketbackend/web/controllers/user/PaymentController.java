package com.mkostadinov.eticketbackend.web.controllers.user;

import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.service.StripeClient;
import com.mkostadinov.eticketbackend.service.TicketService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @GetMapping("/check/default")
    public ResponseEntity<Void> checkForSavedPaymentMethod(Principal principal) {
        return this.stripeClient.checkUserForDefaultPaymentMethod(principal.getName()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDTO> chargeCard(@PathVariable("ticketId") String ticketId, Principal principal, HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        boolean savePaymentMethod = Boolean.parseBoolean(request.getHeader("savePaymentMethod"));

        return ResponseEntity.ok(this.stripeClient.payTicket(false, ticketId, savePaymentMethod, token, principal.getName()));
    }

    @PostMapping("/ticket/default/{ticketId}")
    public ResponseEntity<TicketDTO> defaultPaymentTicket(@PathVariable("ticketId") String ticketId, Principal principal, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(this.stripeClient.payTicket(true, ticketId, null, null, principal.getName()));
    }
}
