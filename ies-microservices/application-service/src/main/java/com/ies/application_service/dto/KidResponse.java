package com.ies.application_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ies.application_service.enums.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KidResponse {

    private Long id;

    private Long applicationId;

    private String childName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String aadhaarNumber;

    private LocalDateTime createdAt;
}