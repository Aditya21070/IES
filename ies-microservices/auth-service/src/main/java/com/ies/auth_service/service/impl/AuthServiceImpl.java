package com.ies.auth_service.service.impl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ies.auth_service.dto.CitizenUserRequest;
import com.ies.auth_service.dto.LoginRequest;
import com.ies.auth_service.dto.LoginResponse;
import com.ies.auth_service.dto.RegisterRequest;
import com.ies.auth_service.dto.UserResponse;
import com.ies.auth_service.entity.User;
import com.ies.auth_service.enums.AccountStatus;
import com.ies.auth_service.enums.Role;
import com.ies.auth_service.exception.InvalidCredentialsException;
import com.ies.auth_service.repository.UserRepository;
import com.ies.auth_service.service.AuthService;
import com.ies.auth_service.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;

	@Override
	public UserResponse findByEmail(String email) {
		User user = userRepository.findByEmail(email)
		        .orElseThrow(() -> new RuntimeException("User not found"));
		
		return UserResponse.builder()
		        .id(user.getId())
		        .name(user.getName())
		        .email(user.getEmail())
		        .phoneNumber(user.getPhoneNumber())
		        .role(user.getRole())
		        .status(user.getStatus())
		        .build();
	}
	
	@Override
	public UserResponse register(RegisterRequest request) {

	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }

	    User user = User.builder()
	            .name(request.getName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .phoneNumber(request.getPhoneNumber())
	            .role(Role.CASE_WORKER)
	            .status(AccountStatus.ACTIVE)
	            .build();
	    
	    User savedUser = userRepository.save(user);
	    
	    return UserResponse.builder()
	            .id(savedUser.getId())
	            .name(savedUser.getName())
	            .email(savedUser.getEmail())
	            .phoneNumber(savedUser.getPhoneNumber())
	            .role(savedUser.getRole())
	            .status(savedUser.getStatus())
	            .build();
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
		        .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
		
		String token = jwtService.generateToken(user);
		
		return LoginResponse.builder()
		        .id(user.getId())
		        .name(user.getName())
		        .email(user.getEmail())
		        .role(user.getRole())
		        .accessToken(token)
		        .build();
	}
	
	@Override
	public UUID createCitizenUser(CitizenUserRequest request) {

	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }

	    User user = new User();

	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
	    user.setPhoneNumber(request.getPhoneNumber());
	    user.setRole(Role.CITIZEN);
	    user.setStatus(AccountStatus.ACTIVE);

	    User savedUser = userRepository.save(user);

	    return savedUser.getId();
	}
}
