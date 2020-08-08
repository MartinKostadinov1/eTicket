package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import com.mkostadinov.eticketbackend.constants.SecurityConstants;
import com.mkostadinov.eticketbackend.exception.payment.PaymentFailedException;
import com.mkostadinov.eticketbackend.exception.payment.PaymentUnableToProceedException;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    private final String USER_PREFIX = "";

    private final TicketService ticketService;

    @Autowired
    StripeClient(TicketService ticketService) {
        this.ticketService = ticketService;
        Stripe.apiKey = "";
    }

    public boolean checkUserForDefaultPaymentMethod(String userId) {
        try {
            Customer customer = Customer.retrieve(this.extractStripeIdFromUserId((userId)));
            return customer != null && customer.getDeleted() == null;
        } catch (Exception e) {
            return false;
        }
    }

    public TicketDTO payTicket(boolean defaultPayment, String ticketId, Boolean savePaymentMethod, String token, String userId) {
        TicketDTO ticketDTO = this.ticketService.findById(ticketId);

        if (ticketDTO == null) {
            throw new PaymentUnableToProceedException(String.format("Ticket with id=%s doesn't exist", ticketId));
        }

        if (ticketDTO.isDeleted()) {
            throw new IllegalArgumentException(String.format("Ticket with id=%s is deleted", ticketId));
        }

        if (ticketDTO.isPaid()) {
            throw new IllegalArgumentException(String.format("Ticket with id=%s is already paid", ticketId));
        }

        Charge charge;
        if (defaultPayment) {
            charge = this.chargeCustomerCard(userId, ticketDTO.getAmount().doubleValue());

        } else {
            charge = this.chargeCreditCard(token, ticketDTO.getAmount().doubleValue(), savePaymentMethod, userId);

        }
        if (charge == null) {
            throw new PaymentFailedException("Payment failed! Payment for this ticket could not be processed!");
        }
        this.ticketService.payTicket(ticketDTO);
        return ticketDTO;
    }

    public Charge chargeCreditCard(String token, double amount, boolean savePaymentMethod, String userId) {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            if (savePaymentMethod) {
                String customer = this.extractStripeIdFromUserId(userId);
                token = this.createCustomer(token, userId).getDefaultSource();
                chargeParams.put("customer", customer);
            }

            chargeParams.put("amount", (int) (amount * 100));
            chargeParams.put("currency", GlobalConstants.APPLICATION_PAYMENT_CURRENCY);
            chargeParams.put("source", token);
            return Charge.create(chargeParams);
        } catch (Exception e) {
            return null;
        }
    }

    public Charge chargeCustomerCard(String userId, double amount) {
        try {
            String customer = this.extractStripeIdFromUserId(userId);
            String sourceCard = Customer.retrieve(customer).getDefaultSource();
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (amount * 100));
            chargeParams.put("currency", GlobalConstants.APPLICATION_PAYMENT_CURRENCY);
            chargeParams.put("customer", customer);
            chargeParams.put("source", sourceCard);
            Charge charge = Charge.create(chargeParams);
            return charge;
        } catch (Exception e) {
            return null;
        }
    }

    private Customer createCustomer(String token, String userId) {
        Map<String, Object> customerParams = new HashMap<>();

        customerParams.put("source", token);
        try {
            Customer check = Customer.retrieve(this.extractStripeIdFromUserId(userId));
            if (check != null && check.getDeleted()) {
                check.update(customerParams);
                return check;
            }
        } catch (Exception e) {
            try {
                customerParams.put("id", this.extractStripeIdFromUserId(userId));
                return Customer.create(customerParams);
            } catch (Exception ex) {
                return null;
            }
        }

        return null;
    }

    private String extractStripeIdFromUserId(String userId) {
        return userId.replaceAll(SecurityConstants.AUTH_PROVIDER_ID_PREFIX_REGEX, USER_PREFIX);
    }

}
