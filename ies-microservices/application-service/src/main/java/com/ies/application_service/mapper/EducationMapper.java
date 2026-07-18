package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.EducationRequest;
import com.ies.application_service.dto.EducationResponse;
import com.ies.application_service.entity.EducationDetails;

@Component
public class EducationMapper {

    public EducationDetails toEntity(EducationRequest request) {

        return EducationDetails.builder()
                .applicationId(request.getApplicationId())
                .highestQualification(request.getHighestQualification())
                .courseName(request.getCourseName())
                .institutionName(request.getInstitutionName())
                .graduationYear(request.getGraduationYear())
                .percentage(request.getPercentage())
                .build();
    }

    public EducationResponse toResponse(EducationDetails education) {

        return EducationResponse.builder()
                .id(education.getId())
                .applicationId(education.getApplicationId())
                .highestQualification(education.getHighestQualification())
                .courseName(education.getCourseName())
                .institutionName(education.getInstitutionName())
                .graduationYear(education.getGraduationYear())
                .percentage(education.getPercentage())
                .createdAt(education.getCreatedAt())
                .build();
    }
}