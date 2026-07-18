package com.ies.application_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.application_service.enums.EmploymentStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeResponse {

    private Long id;

    private Long applicationId;

    private EmploymentStatus employmentStatus;

    private BigDecimal annualIncome;

    private BigDecimal salaryIncome;

    private BigDecimal propertyIncome;

    private LocalDateTime createdAt;
}