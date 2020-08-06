package com.mkostadinov.eticketbackend.web.controllers.dashboard;

import com.google.gson.Gson;
import com.mkostadinov.eticketbackend.model.dto.dashboard.DashboardStatusWidgetDTO;
import com.mkostadinov.eticketbackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final Gson gson;
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(Gson gson, DashboardService dashboardService) {
        this.gson = gson;
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<String> dashboard(Principal principal) {
        return ResponseEntity.ok(this.gson.toJson(this.dashboardService.getUserDashboard(principal)));
    }

    @GetMapping("/status-widget")
    public ResponseEntity<DashboardStatusWidgetDTO> statusWidget (Principal principal) {
        return ResponseEntity.ok(this.dashboardService.getStatusWidget(principal));
    }
}
