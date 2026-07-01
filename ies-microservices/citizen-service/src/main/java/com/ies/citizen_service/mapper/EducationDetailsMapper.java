package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.EducationDetailsResponse;
import com.ies.citizen_service.entity.EducationDetails;

@Component
public class EducationDetailsMapper {

    public EducationDetailsResponse toResponse(EducationDetails education) {

        return EducationDetailsResponse.builder()
                .id(education.getId())
                .applicationId(education.getApplication().getId())
                .highestQualification(education.getHighestQualification())
                .graduationYear(education.getGraduationYear())
                .university(education.getUniversity())
                .build();
    }
}
