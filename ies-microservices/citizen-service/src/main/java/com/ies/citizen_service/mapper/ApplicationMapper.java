package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.ApplicationResponse;
import com.ies.citizen_service.entity.Application;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(Application application) {

        return ApplicationResponse.builder()
                .id(application.getId())
                .applicationNumber(application.getApplicationNumber())
                .citizenId(application.getCitizen().getId())
                .planId(application.getPlanId())
                .status(application.getStatus())
                .createdAt(application.getCreatedAt())
                .build();
    }
}
