package com.mkostadinov.eticketbackend.task;

import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehiclesApiResponseDTO;
import com.mkostadinov.eticketbackend.service.UserService;
import com.mkostadinov.eticketbackend.service.VehicleService;
import com.mkostadinov.eticketbackend.web.external.katApi.VehiclesWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Configuration
@EnableScheduling
public class VehiclesScheduledTasks {

    private final VehicleService vehicleService;
    private final VehiclesWebClient vehiclesWebClient;
    private final Logger log = LoggerFactory.getLogger(VehiclesScheduledTasks.class);


    @Autowired
    public VehiclesScheduledTasks(VehicleService vehicleService, VehiclesWebClient vehiclesWebClient) {
        this.vehicleService = vehicleService;
        this.vehiclesWebClient = vehiclesWebClient;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void addAllNewVehicles() {
        try {
            Flux<VehiclesApiResponseDTO> allRegistrationNumbers = this.vehiclesWebClient.getAllRegistrationNumbers();
            if (allRegistrationNumbers != null) {
                allRegistrationNumbers.subscribe(v -> {
                    this.vehicleService.createVehicle(new VehicleCreationDTO()
                            .setRegistrationNumber(v.getVehicleRegistrationNumber())
                            .setOwnerDrivingLicenseId(v.getOwnerDriverLicenseId()));
                });
            }
        }catch (Exception e) {
            log.error("KAT API connection refused.");
        }

    }

}
