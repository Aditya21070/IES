package com.ies.auth_service.dto;

import lombok.Data;

@Data
public class CitizenUserRequest {

    private String name;

    private String email;

    private String password;

    private String phoneNumber;
}