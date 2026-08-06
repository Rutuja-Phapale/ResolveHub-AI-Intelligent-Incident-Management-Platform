package com.resolvehub.ticket.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resolvehub.ticket.domain.TicketStatus;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

	List<TicketEntity> findByStatus(TicketStatus status);
	
}


