package com.ies.benefit_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.benefit_service.dto.BenefitResponse;
import com.ies.benefit_service.entity.BenefitTransaction;

@Component
public class BenefitMapper {

    public BenefitResponse toResponse(
            BenefitTransaction benefit) {

        return BenefitResponse.builder()
                .id(benefit.getId())
                .applicationId(benefit.getApplicationId())
                .citizenId(benefit.getCitizenId())
                .planId(benefit.getPlanId())
                .benefitAmount(benefit.getBenefitAmount())
                .transactionReference(
                        benefit.getTransactionReference())
                .benefitStatus(benefit.getBenefitStatus())
                .sentAt(benefit.getSentAt())
                .createdAt(benefit.getCreatedAt())
                .build();
    }

}