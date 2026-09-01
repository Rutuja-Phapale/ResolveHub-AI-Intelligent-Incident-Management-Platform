package com.resolvehub.security;

import java.util.Date;

import com.resolvehub.user.infrastructure.persistence.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {

	
	
	private final String secretKey = "resolvehub-secret-key-change-this-in-production-123456789";
	
	public String generateToken(UserEntity user) {
		
		return Jwts.builder()
				.subject(user.getId().toString())
				.claim("email", user.getEmail())
				.claim("role", user.getRole().name())
				.issuedAt(new Date())
				.expiration(
						new Date(System.currentTimeMillis() + 1000 *60*60)
				)
				.signWith(
						Keys.hmacShaKeyFor(secretKey.getBytes())
				).compact();
	}
}
