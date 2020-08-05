package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByRegistrationNumber(String registrationNumber);
}
