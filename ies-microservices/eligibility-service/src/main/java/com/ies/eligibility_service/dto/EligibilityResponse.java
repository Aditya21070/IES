package com.ies.eligibility_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.eligibility_service.enums.EligibilityStatus;

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
public class EligibilityResponse {

    private Long id;

    private Long applicationId;

    private Long citizenId;

    private UUID citizenUserId;

    private Long planId;

    private String planName;

    private EligibilityStatus status;

    private BigDecimal benefitAmount;

    private String denialReason;

    private LocalDateTime processedAt;
}