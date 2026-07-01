package com.ies.auth_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.auth_service.dto.RegisterRequest;
import com.ies.auth_service.dto.UserResponse;
import com.ies.auth_service.entity.User;
import com.ies.auth_service.enums.AccountStatus;
import com.ies.auth_service.enums.Role;
import com.ies.auth_service.repository.UserRepository;
import com.ies.auth_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

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
	            .password(request.getPassword()) // We'll encrypt this later
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
}
