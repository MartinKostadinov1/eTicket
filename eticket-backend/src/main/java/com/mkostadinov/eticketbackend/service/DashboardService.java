package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.model.dto.dashboard.DashboardDTO;
import com.mkostadinov.eticketbackend.model.dto.dashboard.DashboardStatusWidgetDTO;

import java.security.Principal;

public interface DashboardService {

    DashboardDTO getUserDashboard(Principal principal);

    DashboardStatusWidgetDTO getStatusWidget(Principal principal);
}
