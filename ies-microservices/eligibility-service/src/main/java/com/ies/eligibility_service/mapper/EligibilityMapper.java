package com.ies.eligibility_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.eligibility_service.dto.EligibilityResponse;
import com.ies.eligibility_service.entity.EligibilityResult;

@Component
public class EligibilityMapper {

    public EligibilityResponse toResponse(
            EligibilityResult result) {

        return EligibilityResponse.builder()
                .id(result.getId())
                .applicationId(result.getApplicationId())
                .citizenId(result.getCitizenId())
                .citizenUserId(result.getCitizenUserId())
                .planId(result.getPlanId())
                .planName(result.getPlanName())
                .status(result.getStatus())
                .benefitAmount(result.getBenefitAmount())
                .denialReason(result.getDenialReason())
                .processedAt(result.getProcessedAt())
                .build();
    }
}