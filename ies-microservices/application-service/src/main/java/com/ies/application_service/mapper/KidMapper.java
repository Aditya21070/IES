package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.KidRequest;
import com.ies.application_service.dto.KidResponse;
import com.ies.application_service.entity.KidDetails;

@Component
public class KidMapper {

    public KidDetails toEntity(KidRequest request) {

        return KidDetails.builder()
                .applicationId(request.getApplicationId())
                .childName(request.getChildName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .aadhaarNumber(request.getAadhaarNumber())
                .build();
    }

    public KidResponse toResponse(KidDetails kid) {

        return KidResponse.builder()
                .id(kid.getId())
                .applicationId(kid.getApplicationId())
                .childName(kid.getChildName())
                .gender(kid.getGender())
                .dateOfBirth(kid.getDateOfBirth())
                .aadhaarNumber(kid.getAadhaarNumber())
                .createdAt(kid.getCreatedAt())
                .build();
    }
}