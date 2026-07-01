package com.ies.citizen_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.IncomeDetailsRequest;
import com.ies.citizen_service.dto.IncomeDetailsResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.IncomeDetails;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.IncomeDetailsMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.IncomeDetailsRepository;
import com.ies.citizen_service.service.IncomeDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

    private final IncomeDetailsRepository incomeDetailsRepository;
    private final ApplicationRepository applicationRepository;
    private final IncomeDetailsMapper incomeDetailsMapper;

    @Override
    public IncomeDetailsResponse saveIncomeDetails(IncomeDetailsRequest request) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        IncomeDetails incomeDetails = IncomeDetails.builder()
                .application(application)
                .annualIncome(request.getAnnualIncome())
                .employmentStatus(request.getEmploymentStatus())
                .occupation(request.getOccupation())
                .employerName(request.getEmployerName())
                .build();

        IncomeDetails saved = incomeDetailsRepository.save(incomeDetails);

        return incomeDetailsMapper.toResponse(saved);
    }
    
    @Override
    public IncomeDetailsResponse getIncomeDetails(Long applicationId) {

        IncomeDetails incomeDetails = incomeDetailsRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Income details not found"));

        return incomeDetailsMapper.toResponse(incomeDetails);
    }
}
