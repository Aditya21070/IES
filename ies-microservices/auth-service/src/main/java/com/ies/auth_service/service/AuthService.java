package com.ies.auth_service.service;

import java.util.UUID;

import com.ies.auth_service.dto.CitizenUserRequest;
import com.ies.auth_service.dto.LoginRequest;
import com.ies.auth_service.dto.LoginResponse;
import com.ies.auth_service.dto.RegisterRequest;
import com.ies.auth_service.dto.UserResponse;

public interface AuthService {
	UserResponse findByEmail(String email);
	UserResponse register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
	UUID createCitizenUser(CitizenUserRequest request);
}
