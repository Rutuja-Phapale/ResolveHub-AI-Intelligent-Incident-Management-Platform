package com.resolvehub.user.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.resolvehub.ticket.web.dto.LoginRequest;
import com.resolvehub.ticket.web.dto.LoginResponse;
import com.resolvehub.user.application.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		
		return authService.login(request);
	}
	

}
