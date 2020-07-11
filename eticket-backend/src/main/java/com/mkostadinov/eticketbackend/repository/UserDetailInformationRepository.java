package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.UserDetailInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailInformationRepository extends JpaRepository<UserDetailInformation, String> {
}
