package com.ies.eligibility_service.dto.feign;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.eligibility_service.enums.EmploymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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