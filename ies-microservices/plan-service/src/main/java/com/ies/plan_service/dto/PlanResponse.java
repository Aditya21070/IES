package com.ies.plan_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanResponse {

    private Long id;

    private String planName;

    private String planCategory;

    private String description;

    private BigDecimal benefitAmount;

    private Integer minAge;

    private Integer maxAge;

    private BigDecimal incomeLimit;

    private Boolean employmentRequired;

    private Integer maxChildren;

    private Boolean active;

    private LocalDateTime createdAt;
}