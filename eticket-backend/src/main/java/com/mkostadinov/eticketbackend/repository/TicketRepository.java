package com.mkostadinov.eticketbackend.repository;

import com.mkostadinov.eticketbackend.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
