package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.IncomeDetailsResponse;
import com.ies.citizen_service.entity.IncomeDetails;

@Component
public class IncomeDetailsMapper {

    public IncomeDetailsResponse toResponse(IncomeDetails income) {

        return IncomeDetailsResponse.builder()
                .id(income.getId())
                .applicationId(income.getApplication().getId())
                .annualIncome(income.getAnnualIncome())
                .employmentStatus(income.getEmploymentStatus())
                .occupation(income.getOccupation())
                .employerName(income.getEmployerName())
                .build();
    }
}
