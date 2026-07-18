package com.ies.plan_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanRequest {

    @NotBlank(message = "Plan name is required")
    private String planName;

    @NotBlank(message = "Plan category is required")
    private String planCategory;

    private String description;

    @NotNull(message = "Benefit amount is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Benefit amount must be greater than zero")
    private BigDecimal benefitAmount;

    private Integer minAge;

    private Integer maxAge;

    private BigDecimal incomeLimit;

    private Boolean employmentRequired;

    private Integer maxChildren;

    private Boolean active;
}