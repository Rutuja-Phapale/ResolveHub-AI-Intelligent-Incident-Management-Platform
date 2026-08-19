package com.resolvehub.ticket.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resolvehub.ticket.application.exception.TicketNotFoundException;
import com.resolvehub.ticket.application.exception.UserNotFoundException;
import com.resolvehub.ticket.infrastructure.persistence.TicketEntity;
import com.resolvehub.ticket.infrastructure.persistence.TicketRepository;
import com.resolvehub.ticket.web.dto.AssignTicketRequest;
import com.resolvehub.ticket.web.dto.CreateTicketRequest;
import com.resolvehub.ticket.web.dto.TicketResponse;
import com.resolvehub.ticket.web.dto.UpdateTicketRequest;
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
                .orElseThrow(() -> new TicketNotFoundException("User not found"));

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
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return mapToResponse(ticket);
    }

    public List<TicketResponse> getAllTickets() {


        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    
    public TicketResponse updateTicket(UUID ticketid, UpdateTicketRequest request) {
    	
    	TicketEntity ticket = ticketRepository.findById(ticketid)
    	.orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
    	
    	ticket.updateDetails(request.title(),
    			request.description(),
    			request.priority(),
    			request.category(),
    			request.status());
    	
		return mapToResponse(ticket);
    	
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
    
    public TicketResponse assignTicket(
    		UUID ticketId, AssignTicketRequest request) {
    	
    	TicketEntity ticket = ticketRepository.findById(ticketId)
    			.orElseThrow(() -> new TicketNotFoundException("Ticket not found: " +ticketId));
    	
    	UserEntity assignedTo = userRepository.findById(request.assignToUserId())
    			.orElseThrow(() -> new UserNotFoundException("User not found: " +request.assignToUserId()));
    	
    	ticket.assignTo(assignedTo);
    	
    	return mapToResponse(ticket);
    	
    }
    
    
    
    
}