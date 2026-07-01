package com.ies.citizen_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDetailsRequest {

    @NotNull
    private Long applicationId;

    @NotNull
    private Double annualIncome;

    @NotBlank
    private String employmentStatus;

    private String occupation;

    private String employerName;
}
