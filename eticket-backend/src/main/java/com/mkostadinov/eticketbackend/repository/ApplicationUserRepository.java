package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
}
