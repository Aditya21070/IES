package com.ies.application_service.dto.feign;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.application_service.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenResponse {

    private Long id;

    private String citizenNumber;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Boolean portalAccess;

    private LocalDate dob;

    private Gender gender;

    private String aadhaarNumber;

    private UUID userId;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private LocalDateTime createdAt;
}