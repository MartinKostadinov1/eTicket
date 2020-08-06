package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.model.dto.user.UserDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleCreationDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehicleDTO;
import com.mkostadinov.eticketbackend.model.dto.vehicle.VehiclesApiResponseDTO;
import com.mkostadinov.eticketbackend.model.entity.User;
import com.mkostadinov.eticketbackend.model.entity.Vehicle;
import com.mkostadinov.eticketbackend.repository.UserRepository;
import com.mkostadinov.eticketbackend.repository.VehicleRepository;
import com.mkostadinov.eticketbackend.service.UserService;
import com.mkostadinov.eticketbackend.service.VehicleService;
import com.mkostadinov.eticketbackend.web.external.katApi.VehiclesWebClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final VehiclesWebClient vehiclesWebClient;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository, ModelMapper modelMapper, VehiclesWebClient vehiclesWebClient) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.vehiclesWebClient = vehiclesWebClient;
    }

    @Override
    public void createVehicle(VehicleCreationDTO vehicle) {
        UserDTO user = this.userRepository.findByDriverLicenseId(vehicle.getOwnerDrivingLicenseId()).map(u -> this.modelMapper.map(u, UserDTO.class)).orElse(null);
        if (user != null && !this.vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber())) {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setRegistrationNumber(vehicle.getRegistrationNumber())
                    .setTickets(new LinkedHashSet<>())
                    .setAddedOn(LocalDateTime.now())
                    .setBlocked(false);


            this.vehicleRepository.saveAndFlush(newVehicle);

            user.getVehicles().add(this.modelMapper.map(newVehicle, VehicleDTO.class));

            this.userRepository.saveAndFlush(this.modelMapper.map(user, User.class));
        }
    }

    @Override
    public void flushNewUserVehicles(String drivingLicenseIds) {
        Flux<VehiclesApiResponseDTO> registrationNumbers = this.vehiclesWebClient.getRegistrationNumbers(drivingLicenseIds);
        if (registrationNumbers != null) {
            registrationNumbers.subscribe(v -> {
                if(!this.vehicleRepository.existsByRegistrationNumber(v.getVehicleRegistrationNumber())  && this.userRepository.findByDriverLicenseId(v.getOwnerDriverLicenseId()).orElse(null) != null) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setRegistrationNumber(v.getVehicleRegistrationNumber())
                            .setAddedOn(LocalDateTime.now())
                            .setBlocked(false);

                    this.vehicleRepository.saveAndFlush(newVehicle);

                    VehicleDTO vehicleDTO = this.modelMapper.map(newVehicle, VehicleDTO.class);
                    vehicleDTO.setOwnerDriverLicenseId(v.getOwnerDriverLicenseId());

                    User user = this.userRepository.findByDriverLicenseId(v.getOwnerDriverLicenseId()).orElse(null);

                    if(user != null) {
                        user.getVehicles().add(newVehicle);
                        this.userRepository.saveAndFlush(user);
                    }
                }

            });
        }
    }

    @Override
    public VehicleDTO findByRegistrationNumber(String registrationNumber) {
        return this.vehicleRepository.findByRegistrationNumber(registrationNumber).map(v -> this.modelMapper.map(v, VehicleDTO.class)).orElse(null);
    }

    @Override
    public void saveVehicle(VehicleDTO vehicle) {
        this.vehicleRepository.saveAndFlush(this.modelMapper.map(vehicle, Vehicle.class));
    }
}
