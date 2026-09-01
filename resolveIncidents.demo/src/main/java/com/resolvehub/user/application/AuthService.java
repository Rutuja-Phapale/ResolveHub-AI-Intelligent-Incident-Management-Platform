package com.resolvehub.user.application;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resolvehub.security.JwtService;
import com.resolvehub.ticket.application.exception.UserNotFoundException;
import com.resolvehub.ticket.web.dto.LoginRequest;
import com.resolvehub.ticket.web.dto.LoginResponse;
import com.resolvehub.user.infrastructure.persistence.UserEntity;
import com.resolvehub.user.infrastructure.persistence.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	
	public LoginResponse login(LoginRequest request) {
		
		UserEntity user = userRepository.findByEmail(request.email())
		.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

		boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPasswordHash());
		
		if(!passwordMatches) {
			throw new IllegalArgumentException("Invalid email or password");
			
		}
		
		String token = jwtService.generateToken(user);
		
		
		return new LoginResponse(token);
	}
	
}
