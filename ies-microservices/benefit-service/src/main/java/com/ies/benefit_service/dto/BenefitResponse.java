package com.ies.benefit_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.benefit_service.enums.BenefitStatus;

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
public class BenefitResponse {

    private Long id;

    private Long applicationId;

    private Long citizenId;

    private Long planId;

    private BigDecimal benefitAmount;

    private String transactionReference;

    private BenefitStatus benefitStatus;

    private LocalDateTime sentAt;

    private LocalDateTime createdAt;

}