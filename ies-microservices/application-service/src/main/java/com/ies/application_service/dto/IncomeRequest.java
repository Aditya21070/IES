package com.ies.application_service.dto;

import java.math.BigDecimal;

import com.ies.application_service.enums.EmploymentStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

    @NotNull(message = "Employment Status is required")
    private EmploymentStatus employmentStatus;

    @NotNull(message = "Annual Income is required")
    @DecimalMin(value = "0.0")
    private BigDecimal annualIncome;

    @NotNull(message = "Salary Income is required")
    @DecimalMin(value = "0.0")
    private BigDecimal salaryIncome;

    @NotNull(message = "Property Income is required")
    @DecimalMin(value = "0.0")
    private BigDecimal propertyIncome;
}