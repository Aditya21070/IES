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
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	private final ApplicationSecurityService applicationSecurityService;
	
    private final IncomeDetailsRepository incomeRepository;

    private final ApplicationRepository applicationRepository;

    private final IncomeMapper incomeMapper;

    @Override
    public IncomeResponse saveIncomeDetails(
            IncomeRequest request,
            String token) {

	    	Application application =
	    	        applicationSecurityService.validateOwnership(
	    	                request.getApplicationId(),
	    	                token);

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