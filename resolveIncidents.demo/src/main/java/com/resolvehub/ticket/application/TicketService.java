package com.resolvehub.ticket.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resolvehub.ticket.infrastructure.persistence.TicketEntity;
import com.resolvehub.ticket.infrastructure.persistence.TicketRepository;
import com.resolvehub.ticket.web.dto.CreateTicketRequest;
import com.resolvehub.ticket.web.dto.TicketResponse;
import com.resolvehub.user.infrastructure.persistence.UserEntity;
import com.resolvehub.user.infrastructure.persistence.UserRepository;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(
            TicketRepository ticketRepository,
            UserRepository userRepository) {

        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request) {

        UserEntity createdBy = userRepository.findById(request.createdByUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TicketEntity ticket = new TicketEntity(
                request.title(),
                request.description(),
                createdBy
        );

        TicketEntity savedTicket = ticketRepository.save(ticket);

        return mapToResponse(savedTicket);
    }

    public TicketResponse getTicketById(UUID id) {

        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        return mapToResponse(ticket);
    }

    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TicketResponse mapToResponse(TicketEntity ticket) {

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCategory(),
                ticket.getCreatedBy().getId(),
                ticket.getCreatedBy().getEmail(),
                ticket.getAssignedTo() != null ? ticket.getAssignedTo().getId() : null,
                ticket.getAssignedTo() != null ? ticket.getAssignedTo().getEmail() : null,
                ticket.getVersion(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}