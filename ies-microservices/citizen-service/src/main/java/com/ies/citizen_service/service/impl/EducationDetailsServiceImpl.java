package com.ies.citizen_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.EducationDetailsRequest;
import com.ies.citizen_service.dto.EducationDetailsResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.EducationDetails;
import com.ies.citizen_service.exception.DuplicateResourceException;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.EducationDetailsMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.EducationDetailsRepository;
import com.ies.citizen_service.service.EducationDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationDetailsServiceImpl implements EducationDetailsService {

    private final EducationDetailsRepository educationDetailsRepository;
    private final ApplicationRepository applicationRepository;
    private final EducationDetailsMapper educationDetailsMapper;

    @Override
    public EducationDetailsResponse saveEducationDetails(EducationDetailsRequest request) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (educationDetailsRepository.findByApplicationId(request.getApplicationId()).isPresent()) {
            throw new DuplicateResourceException("Education details already exist for this application");
        }

        EducationDetails educationDetails = EducationDetails.builder()
                .application(application)
                .highestQualification(request.getHighestQualification())
                .graduationYear(request.getGraduationYear())
                .university(request.getUniversity())
                .build();

        EducationDetails saved = educationDetailsRepository.save(educationDetails);

        return educationDetailsMapper.toResponse(saved);
    }

    @Override
    public EducationDetailsResponse getEducationDetails(Long applicationId) {

        EducationDetails educationDetails = educationDetailsRepository
                .findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education details not found"));

        return educationDetailsMapper.toResponse(educationDetails);
    }
}
