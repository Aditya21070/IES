package com.ies.application_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.EducationRequest;
import com.ies.application_service.dto.EducationResponse;
import com.ies.application_service.entity.Application;
import com.ies.application_service.entity.EducationDetails;
import com.ies.application_service.exceptions.DuplicateResourceException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.mapper.EducationMapper;
import com.ies.application_service.repository.ApplicationRepository;
import com.ies.application_service.repository.EducationDetailsRepository;
import com.ies.application_service.service.EducationDetailsService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationDetailsServiceImpl
        implements EducationDetailsService {

    private final EducationDetailsRepository educationRepository;

    private final ApplicationRepository applicationRepository;

    private final EducationMapper educationMapper;
    
    private final ApplicationSecurityService applicationSecurityService;

    @Override
    public EducationResponse saveEducationDetails(
            EducationRequest request,
            String token) {

    	Application application =
    	        applicationSecurityService.validateOwnership(
    	                request.getApplicationId(),
    	                token);

        if (educationRepository.existsByApplicationId(application.getId())) {
            throw new DuplicateResourceException(
                    "Education details already exist for this application.");
        }

        EducationDetails education = educationMapper.toEntity(request);

        EducationDetails savedEducation =
                educationRepository.save(education);

        return educationMapper.toResponse(savedEducation);
    }

    @Override
    public EducationResponse getEducationDetails(Long applicationId) {

        EducationDetails education =
                educationRepository.findByApplicationId(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Education details not found"));

        return educationMapper.toResponse(education);
    }
}