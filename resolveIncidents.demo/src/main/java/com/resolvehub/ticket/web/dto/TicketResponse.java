package com.resolvehub.ticket.web.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.resolvehub.ticket.domain.TicketCategory;
import com.resolvehub.ticket.domain.TicketPriority;
import com.resolvehub.ticket.domain.TicketStatus;

public record TicketResponse(
        UUID id,
        String title,
        String description,
        TicketStatus status,
        TicketPriority priority,
        TicketCategory category,
        UUID createdByUserId,
        String createdByEmail,
        UUID assignedToUserId,
        String assignedToEmail,
        Long version,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}