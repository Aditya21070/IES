package com.ies.application_service.dto;

import com.ies.application_service.enums.MaritalStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

    @NotNull(message = "Marital Status is required")
    private MaritalStatus maritalStatus;

    private String spouseName;

    @Min(value = 18, message = "Spouse age must be at least 18")
    private Integer spouseAge;

    @NotNull(message = "Number of children is required")
    @Min(value = 0, message = "Number of children cannot be negative")
    private Integer numberOfChildren;

}