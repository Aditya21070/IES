package com.ies.application_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.FamilyRequest;
import com.ies.application_service.dto.FamilyResponse;
import com.ies.application_service.entity.FamilyDetails;
import com.ies.application_service.enums.MaritalStatus;
import com.ies.application_service.exceptions.BadRequestException;
import com.ies.application_service.exceptions.DuplicateResourceException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.mapper.FamilyMapper;
import com.ies.application_service.repository.FamilyDetailsRepository;
import com.ies.application_service.service.FamilyDetailsService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyDetailsServiceImpl
        implements FamilyDetailsService {

    private final FamilyDetailsRepository familyRepository;

    private final FamilyMapper familyMapper;

    private final ApplicationSecurityService applicationSecurityService;

    @Override
    public FamilyResponse saveFamilyDetails(
            FamilyRequest request,
            String token) {

        applicationSecurityService.validateOwnership(
                request.getApplicationId(),
                token);

        if (familyRepository.existsByApplicationId(request.getApplicationId())) {
            throw new DuplicateResourceException(
                    "Family details already exist for this application.");
        }

        validateFamily(request);

        FamilyDetails family = familyMapper.toEntity(request);

        FamilyDetails savedFamily =
                familyRepository.save(family);

        return familyMapper.toResponse(savedFamily);
    }

    @Override
    public FamilyResponse getFamilyDetails(Long applicationId) {

        FamilyDetails family =
                familyRepository.findByApplicationId(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Family details not found"));

        return familyMapper.toResponse(family);
    }

    private void validateFamily(FamilyRequest request) {

        if (request.getMaritalStatus() == MaritalStatus.MARRIED) {

            if (request.getSpouseName() == null ||
                request.getSpouseName().isBlank()) {

                throw new BadRequestException(
                        "Spouse name is required for married citizens.");
            }

            if (request.getSpouseAge() == null) {

                throw new BadRequestException(
                        "Spouse age is required for married citizens.");
            }

        } else {

            request.setSpouseName(null);
            request.setSpouseAge(null);
        }
    }
}