package com.mkostadinov.eticketbackend.task;

import com.mkostadinov.eticketbackend.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VehiclesScheduledTasks {

    private final VehicleService vehicleService;
    private final Logger log = LoggerFactory.getLogger(VehiclesScheduledTasks.class);

    @Autowired
    public VehiclesScheduledTasks(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void addAllNewVehicles() {
        log.info("Task is vehicles - run");
    }

}
