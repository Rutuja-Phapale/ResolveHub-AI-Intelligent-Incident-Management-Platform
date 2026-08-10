package com.resolvehub.ticket.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.resolvehub.ticket.application.TicketService;
import com.resolvehub.ticket.web.dto.CreateTicketRequest;
import com.resolvehub.ticket.web.dto.TicketResponse;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	private final TicketService ticketService;
	
	public TicketController( TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TicketResponse createTicket( @RequestBody CreateTicketRequest request) {
		return ticketService.createTicket(request);
	}
	
	@GetMapping("/{id}")
	public TicketResponse getTicketById(@PathVariable UUID id ) {
	     return ticketService.getTicketById(id);	
	}
	
	@GetMapping
	List<TicketResponse> getAllTickets(){
		return ticketService.getAllTickets();
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
