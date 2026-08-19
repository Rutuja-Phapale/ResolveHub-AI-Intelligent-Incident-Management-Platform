package com.resolvehub.ticket.web.dto;

public record ErrorResponse (
	
	int status,
	String error,
	String message,
	String path
) {
}
