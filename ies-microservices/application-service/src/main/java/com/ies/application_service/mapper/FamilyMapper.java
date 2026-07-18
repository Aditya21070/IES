package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.FamilyRequest;
import com.ies.application_service.dto.FamilyResponse;
import com.ies.application_service.entity.FamilyDetails;

@Component
public class FamilyMapper {

    public FamilyDetails toEntity(FamilyRequest request) {

        return FamilyDetails.builder()
                .applicationId(request.getApplicationId())
                .maritalStatus(request.getMaritalStatus())
                .spouseName(request.getSpouseName())
                .spouseAge(request.getSpouseAge())
                .numberOfChildren(request.getNumberOfChildren())
                .build();
    }

    public FamilyResponse toResponse(FamilyDetails family) {

        return FamilyResponse.builder()
                .id(family.getId())
                .applicationId(family.getApplicationId())
                .maritalStatus(family.getMaritalStatus())
                .spouseName(family.getSpouseName())
                .spouseAge(family.getSpouseAge())
                .numberOfChildren(family.getNumberOfChildren())
                .createdAt(family.getCreatedAt())
                .build();
    }
}