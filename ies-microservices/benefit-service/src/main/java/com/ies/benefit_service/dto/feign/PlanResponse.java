package com.ies.benefit_service.dto.feign;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanResponse {

    private Long id;

    private String planName;

    private BigDecimal benefitAmount;

}