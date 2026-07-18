package com.ies.application_service.dto;

import java.time.LocalDate;

import com.ies.application_service.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KidRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

    @NotBlank(message = "Child name is required")
    private String childName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private String aadhaarNumber;
}