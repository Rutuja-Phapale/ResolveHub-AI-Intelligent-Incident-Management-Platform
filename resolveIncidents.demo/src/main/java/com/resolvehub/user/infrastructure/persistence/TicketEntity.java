package com.resolvehub.user.infrastructure.persistence;

import java.util.UUID;

import com.resolvehub.user.domain.TicketCategory;
import com.resolvehub.user.domain.TicketPriority;
import com.resolvehub.user.domain.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity

public class TicketEntity {

	@Id
	private UUID id;
	
	@Column(length = 150,nullable = false)
	public String title;
	
	
	@Column(nullable= false, columnDefinition = "TEXT")
	public String description;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketPriority priority;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketCategory category;
	
	
}
