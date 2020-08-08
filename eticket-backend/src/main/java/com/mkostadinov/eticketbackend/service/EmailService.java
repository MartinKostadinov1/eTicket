package com.mkostadinov.eticketbackend.service;


import com.mkostadinov.eticketbackend.constants.GlobalConstants;
import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailService {

    public final JavaMailSender emailSender;
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void sendNewTicketEmail(VehicleDTO vehicleDTO, String ticketDescription, LocalDateTime timeCreated) {
        try {
            UserDTO userDTO = this.userService.findByVehiclesContaining(vehicleDTO);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userDTO.getEmail());
            message.setSubject("eTicket - new ticket");

            message.setText(String.format("Hey %s there is a new ticket from %s at %s. Go and check at %s for more details!", userDTO.getFirstName(), ticketDescription, timeCreated, GlobalConstants.FRONTEND_URL + "/etickets"));
            emailSender.send(message);
        }catch (Exception e) {
            log.error("Could not send new ticket email");
        }
    }
}
