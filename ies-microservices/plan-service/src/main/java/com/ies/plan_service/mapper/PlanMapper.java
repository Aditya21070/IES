package com.ies.plan_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.plan_service.dto.PlanRequest;
import com.ies.plan_service.dto.PlanResponse;
import com.ies.plan_service.entity.Plan;

@Component
public class PlanMapper {

    public Plan toEntity(PlanRequest request) {

        return Plan.builder()
                .planName(request.getPlanName())
                .planCategory(request.getPlanCategory())
                .description(request.getDescription())
                .benefitAmount(request.getBenefitAmount())
                .minAge(request.getMinAge())
                .maxAge(request.getMaxAge())
                .incomeLimit(request.getIncomeLimit())
                .employmentRequired(request.getEmploymentRequired())
                .maxChildren(request.getMaxChildren())
                .active(request.getActive())
                .build();
    }

    public PlanResponse toResponse(Plan plan) {

        return PlanResponse.builder()
                .id(plan.getId())
                .planName(plan.getPlanName())
                .planCategory(plan.getPlanCategory())
                .description(plan.getDescription())
                .benefitAmount(plan.getBenefitAmount())
                .minAge(plan.getMinAge())
                .maxAge(plan.getMaxAge())
                .incomeLimit(plan.getIncomeLimit())
                .employmentRequired(plan.getEmploymentRequired())
                .maxChildren(plan.getMaxChildren())
                .active(plan.getActive())
                .createdAt(plan.getCreatedAt())
                .build();
    }
}