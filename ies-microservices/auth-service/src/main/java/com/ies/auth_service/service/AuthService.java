package com.ies.auth_service.service;

import com.ies.auth_service.dto.RegisterRequest;
import com.ies.auth_service.dto.UserResponse;

public interface AuthService {
	UserResponse findByEmail(String email);
	UserResponse register(RegisterRequest request);
}
