package com.ies.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password should be at least 6 characters")
	private String password;

	@NotBlank(message = "Phone number is required")
	@Pattern(
	    regexp = "^[0-9]{10}$",
	    message = "Phone number must contain exactly 10 digits"
	)
	private String phoneNumber;
}
