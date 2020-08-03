package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAuthProviderId(String authProviderId);

    Optional<User> findByDriverLicenseId(String drivingLicenseId);

}
