package com.resolvehub.ticket.application.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
