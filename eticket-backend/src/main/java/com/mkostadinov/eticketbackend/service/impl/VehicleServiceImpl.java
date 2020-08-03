package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.repository.VehicleRepository;
import com.mkostadinov.eticketbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public void addAllUsersVehicles() {

    }
}
