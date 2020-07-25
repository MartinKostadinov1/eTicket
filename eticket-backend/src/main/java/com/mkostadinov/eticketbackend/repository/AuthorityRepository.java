package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.ApplicationAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationAuthorityRepository extends JpaRepository<ApplicationAuthority, String> {
}
