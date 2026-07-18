package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.IncomeRequest;
import com.ies.application_service.dto.IncomeResponse;
import com.ies.application_service.entity.IncomeDetails;

@Component
public class IncomeMapper {

    public IncomeDetails toEntity(IncomeRequest request) {

        return IncomeDetails.builder()
                .applicationId(request.getApplicationId())
                .employmentStatus(request.getEmploymentStatus())
                .annualIncome(request.getAnnualIncome())
                .salaryIncome(request.getSalaryIncome())
                .propertyIncome(request.getPropertyIncome())
                .build();
    }

    public IncomeResponse toResponse(IncomeDetails income) {

        return IncomeResponse.builder()
                .id(income.getId())
                .applicationId(income.getApplicationId())
                .employmentStatus(income.getEmploymentStatus())
                .annualIncome(income.getAnnualIncome())
                .salaryIncome(income.getSalaryIncome())
                .propertyIncome(income.getPropertyIncome())
                .createdAt(income.getCreatedAt())
                .build();
    }
}