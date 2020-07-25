package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
