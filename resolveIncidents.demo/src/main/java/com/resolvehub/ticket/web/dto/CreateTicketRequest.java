package com.resolvehub.ticket.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest(

	@NotBlank(message="Title is required")
	@Size(max=150, message="Title must be less than 150 characters")
	String title,
	
	@NotBlank(message = "Description is required")
	String description,
	
	@NotNull(message = "CreatedByUserid is required for day 2 testing")
	UUID createdByUserID
	) {
}
