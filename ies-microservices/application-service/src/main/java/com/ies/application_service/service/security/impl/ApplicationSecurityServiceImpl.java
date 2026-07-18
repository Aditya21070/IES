package com.ies.application_service.service.security.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ies.application_service.entity.Application;
import com.ies.application_service.exceptions.ForbiddenException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.repository.ApplicationRepository;
import com.ies.application_service.security.JwtService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationSecurityServiceImpl
        implements ApplicationSecurityService {

    private final ApplicationRepository applicationRepository;

    private final JwtService jwtService;

    @Override
    public Application validateOwnership(
            Long applicationId,
            String token) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found"));

        UUID loggedInUser =
                jwtService.extractUserId(token);

        if (!application.getCitizenUserId().equals(loggedInUser)) {

            throw new ForbiddenException(
                    "You are not allowed to update this application.");
        }

        return application;
    }
}