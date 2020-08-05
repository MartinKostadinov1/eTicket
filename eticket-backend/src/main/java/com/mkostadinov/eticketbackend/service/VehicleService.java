package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;

import java.util.List;

public interface VehicleService {

    void createVehicle(VehicleCreationDTO vehicle);
    void flushNewUserVehicles(String drivingLicenseIds);
}
