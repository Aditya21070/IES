package com.ies.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.auth_service.dto.LoginRequest;
import com.ies.auth_service.dto.LoginResponse;
import com.ies.auth_service.dto.RegisterRequest;
import com.ies.auth_service.dto.UserResponse;
import com.ies.auth_service.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {

		UserResponse response = authService.findByEmail(email);

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {

	    UserResponse response = authService.register(request);

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

	    LoginResponse response = authService.login(request);

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/me")
	public ResponseEntity<String> me(Authentication authentication) {

	    return ResponseEntity.ok(authentication.getName());
	}
}
