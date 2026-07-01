package com.ies.auth_service.dto;

import java.util.UUID;

import com.ies.auth_service.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private UUID id;

	private String name;

	private String email;

	private Role role;
	
	private String accessToken;
}
