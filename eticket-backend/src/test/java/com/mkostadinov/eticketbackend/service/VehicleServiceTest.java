package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.BaseTest;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import com.mkostadinov.eticketbackend.model.entity.Vehicle;
import com.mkostadinov.eticketbackend.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

public class VehicleServiceTest extends BaseTest {

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    private Vehicle toCreate;

    @Override
    protected void beforeEach() {
        super.beforeEach();

        this.toCreate = new Vehicle() {{
            setBlocked(false);
            setAddedOn(LocalDateTime.now());
            setTickets(new HashSet<>());
            setRegistrationNumber("CA 1234 CA");
            setId("1");
        }};
    }

    @Test
    public void findVehicle() {
        VehicleDTO vehicle = this.vehicleService.findByRegistrationNumber("BP 1234 BP");

        Assertions.assertEquals("BP 1234 BP", vehicle.getRegistrationNumber());

    }

    @Test
    public void findVehicleShouldBeNull() {
        VehicleDTO vehicle = this.vehicleService.findByRegistrationNumber("BP 3456 BP");

        Assertions.assertNull(vehicle);
    }
}
