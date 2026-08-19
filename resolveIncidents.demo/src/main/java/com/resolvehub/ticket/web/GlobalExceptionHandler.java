package com.resolvehub.ticket.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.resolvehub.ticket.application.exception.TicketNotFoundException;
import com.resolvehub.ticket.application.exception.UserNotFoundException;
import com.resolvehub.ticket.web.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TicketNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleTicketNotFound(
			TicketNotFoundException exception,
			HttpServletRequest request
			) {
		
		return new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				"TICKET_NOT_FOUND",
				exception.getMessage(),
				request.getRequestURI()
				);
	}
	
	
	public ErrorResponse handleUserNotFound(
			UserNotFoundException exception,
			HttpServletRequest request) {
		
		return new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				"USER_NOT_FOUND",
				exception.getMessage(),
				request.getRequestURI());
	}

}
