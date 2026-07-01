package com.ies.auth_service.service;

public interface JwtService {
	String generateToken(String email);
	String extractUsername(String token);
	boolean isTokenValid(String token, String email);
}
