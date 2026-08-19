package com.resolvehub.ticket.web.dto;

import com.resolvehub.ticket.domain.TicketCategory;
import com.resolvehub.ticket.domain.TicketPriority;
import com.resolvehub.ticket.domain.TicketStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTicketRequest(
		
		@NotBlank(message = "Title is required")
		@Size(max = 150, message = "Title must not exceed 150 characters")
		String title,
		
		@NotBlank(message = "Description is required")
		String description,
		
		@NotNull(message = "Priority is required")
		TicketPriority priority,
		
		@NotNull(message ="Category is required")
		TicketCategory category,
		
		@NotNull(message = "Status is required")
		TicketStatus status		
		)
{}
