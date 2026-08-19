package com.resolvehub.ticket.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AssignTicketRequest (

	@NotNull(message = "Assigned user ID is required")
	UUID assignToUserId
	) {
}
