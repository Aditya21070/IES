package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.ApplicationResponse;
import com.ies.application_service.entity.Application;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(Application application) {

        return ApplicationResponse.builder()
                .id(application.getId())
                .applicationNumber(application.getApplicationNumber())
                .citizenId(application.getCitizenId())
                .citizenUserId(application.getCitizenUserId())
                .planId(application.getPlanId())
                .status(application.getStatus())
                .createdAt(application.getCreatedAt())
                .build();
    }

}