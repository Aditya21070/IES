package com.ies.application_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.IncomeRequest;
import com.ies.application_service.dto.IncomeResponse;
import com.ies.application_service.entity.Application;
import com.ies.application_service.entity.IncomeDetails;
import com.ies.application_service.exceptions.DuplicateResourceException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.mapper.IncomeMapper;
import com.ies.application_service.repository.ApplicationRepository;
import com.ies.application_service.repository.IncomeDetailsRepository;
import com.ies.application_service.service.IncomeDetailsService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import com.ies.application_service.security.JwtService;
import com.ies.application_service.exceptions.ForbiddenException;

@Service
@RequiredArgsConstructor
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	private final JwtService jwtService;
	
    private final IncomeDetailsRepository incomeRepository;

    private final ApplicationRepository applicationRepository;

    private final IncomeMapper incomeMapper;

    @Override
    public IncomeResponse saveIncomeDetails(
            IncomeRequest request,
            String token) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        UUID loggedInUser = jwtService.extractUserId(token);

        if (!application.getCitizenUserId().equals(loggedInUser)) {
            throw new ForbiddenException(
                    "You are not allowed to update this application.");
        }

        if (incomeRepository.existsByApplicationId(application.getId())) {
            throw new DuplicateResourceException(
                    "Income details already exist for this application");
        }

        IncomeDetails income = incomeMapper.toEntity(request);

        IncomeDetails savedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(savedIncome);
    }

    @Override
    public IncomeResponse getIncomeDetails(Long applicationId) {

        IncomeDetails income = incomeRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income details not found"));

        return incomeMapper.toResponse(income);
    }
}