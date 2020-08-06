package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.model.dto.dashboard.DashboardDTO;
import com.mkostadinov.eticketbackend.model.dto.dashboard.DashboardStatusWidgetDTO;
import com.mkostadinov.eticketbackend.model.dto.ticket.TicketDTO;
import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import com.mkostadinov.eticketbackend.service.DashboardService;
import com.mkostadinov.eticketbackend.service.TicketService;
import com.mkostadinov.eticketbackend.service.UserService;
import com.mkostadinov.eticketbackend.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final VehicleService vehicleService;
    private final TicketService ticketService;

    @Autowired
    public DashboardServiceImpl(ModelMapper modelMapper, UserService userService, VehicleService vehicleService, TicketService ticketService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.ticketService = ticketService;
    }

    @Override
    public DashboardDTO getUserDashboard(Principal principal) {
        UserDTO user = this.userService.findCurrentUser(principal);


        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO
                .setAuthProviderId(user.getAuthProviderId())
                .setDriverLicenseId(user.getDriverLicenseId())
                .setEmail(user.getEmail())
                .setVehicles(user.getVehicles());

        System.out.println();
        return dashboardDTO;
    }

    @Override
    public DashboardStatusWidgetDTO getStatusWidget(Principal principal) {
        UserDTO user = this.userService.findCurrentUser(principal);

        DashboardStatusWidgetDTO dashboardStatusWidgetDTO = new DashboardStatusWidgetDTO();

        dashboardStatusWidgetDTO.setVehicleCount(user.getVehicles().size());


        LocalDateTime previousMonthDate = LocalDateTime
                .now()
                .minusMonths(1).withDayOfMonth(1)
                .minusHours(0).withMinute(0).minusSeconds(0).withNano(0);

        LocalDateTime currentMonthDate = LocalDateTime
                .now()
                .withDayOfMonth(1)
                .minusHours(0).withMinute(0).minusSeconds(0).withNano(0);

        List<TicketDTO> allUserTickets = new ArrayList<>();

        user.getVehicles().forEach(v -> allUserTickets.addAll(v.getTickets()));

        dashboardStatusWidgetDTO.setAllPaidTickets((int) allUserTickets.stream().filter(TicketDTO::isPaid).count());

        List<TicketDTO> previousMonthTickets = allUserTickets.stream().filter(t -> (t.getCreatedOn().isEqual(previousMonthDate) || t.getCreatedOn().isAfter(previousMonthDate)) && t.getCreatedOn().isBefore(currentMonthDate)).collect(Collectors.toList());
        List<TicketDTO> monthlyTickets = allUserTickets.stream().filter(t -> t.getCreatedOn().isEqual(currentMonthDate) || t.getCreatedOn().isAfter(currentMonthDate)).collect(Collectors.toList());

        dashboardStatusWidgetDTO.setMonthlyTrips(monthlyTickets.size());
        dashboardStatusWidgetDTO.setPreviousMonthTrips(previousMonthTickets.size());

         dashboardStatusWidgetDTO.setUnpaidTickets((int) monthlyTickets.stream().filter(t -> !t.isPaid()).count());


         return dashboardStatusWidgetDTO;
    }
}
