package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.FamilyDetailsResponse;
import com.ies.citizen_service.entity.FamilyDetails;

@Component
public class FamilyDetailsMapper {

    public FamilyDetailsResponse toResponse(FamilyDetails family) {

        return FamilyDetailsResponse.builder()
                .id(family.getId())
                .applicationId(family.getApplication().getId())
                .maritalStatus(family.getMaritalStatus())
                .spouseName(family.getSpouseName())
                .familyMembers(family.getFamilyMembers())
                .build();
    }
}
