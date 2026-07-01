package com.ies.auth_service.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ies.auth_service.entity.User;
import com.ies.auth_service.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;
	
	private SecretKey getSigningKey() {
	    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	private Date extractExpiration(String token) {
	    return Jwts.parser()
	            .verifyWith((SecretKey) getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getExpiration();
	}
	
	private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	}
	
	@Override
	public String generateToken(User user) {

		return Jwts.builder()
		        .subject(user.getEmail())
		        .claim("role", user.getRole().name())
		        .claim("userId", user.getId().toString())
		        .issuedAt(new Date())
		        .expiration(new Date(System.currentTimeMillis() + expiration))
		        .signWith(getSigningKey())
		        .compact();
	}
	
	@Override
	public String extractUsername(String token) {
	    return Jwts.parser()
	            .verifyWith((SecretKey) getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	
	@Override
	public boolean isTokenValid(String token, String email) {

	    String username = extractUsername(token);

	    return username.equals(email) && !isTokenExpired(token);
	}
	
	@Override
	public UUID extractUserId(String token) {

	    String id = Jwts.parser()
	            .verifyWith(getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .get("userId", String.class);

	    return UUID.fromString(id);
	}
}
