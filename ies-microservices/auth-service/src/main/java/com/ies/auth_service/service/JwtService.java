package com.ies.auth_service.service;

import java.util.UUID;

import com.ies.auth_service.entity.User;

public interface JwtService {
	public String generateToken(User user);
	String extractUsername(String token);
	boolean isTokenValid(String token, String email);
	UUID extractUserId(String token);
}
