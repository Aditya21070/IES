package com.ies.citizen_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.FamilyDetailsRequest;
import com.ies.citizen_service.dto.FamilyDetailsResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.FamilyDetails;
import com.ies.citizen_service.exception.DuplicateResourceException;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.FamilyDetailsMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.FamilyDetailsRepository;
import com.ies.citizen_service.service.FamilyDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyDetailsServiceImpl implements FamilyDetailsService {

    private final FamilyDetailsRepository familyDetailsRepository;
    private final ApplicationRepository applicationRepository;
    private final FamilyDetailsMapper familyDetailsMapper;

    @Override
    public FamilyDetailsResponse saveFamilyDetails(FamilyDetailsRequest request) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (familyDetailsRepository.findByApplicationId(request.getApplicationId()).isPresent()) {
            throw new DuplicateResourceException("Family details already exist");
        }

        FamilyDetails familyDetails = FamilyDetails.builder()
                .application(application)
                .maritalStatus(request.getMaritalStatus())
                .spouseName(request.getSpouseName())
                .familyMembers(request.getFamilyMembers())
                .build();

        FamilyDetails saved = familyDetailsRepository.save(familyDetails);

        return familyDetailsMapper.toResponse(saved);
    }

    @Override
    public FamilyDetailsResponse getFamilyDetails(Long applicationId) {

        FamilyDetails familyDetails = familyDetailsRepository
                .findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Family details not found"));

        return familyDetailsMapper.toResponse(familyDetails);
    }
}
