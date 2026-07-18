package com.ies.application_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;
import com.ies.application_service.dto.feign.CitizenResponse;
import com.ies.application_service.entity.Application;
import com.ies.application_service.enums.ApplicationStatus;
import com.ies.application_service.exceptions.BadRequestException;
import com.ies.application_service.feign.CitizenFeignClient;
import com.ies.application_service.mapper.ApplicationMapper;
import com.ies.application_service.repository.ApplicationRepository;
import com.ies.application_service.security.JwtService;
import com.ies.application_service.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
	
	private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    private final JwtService jwtService;

    private final CitizenFeignClient citizenFeignClient;
    
    @Override
    public ApplicationResponse createApplication(
            ApplicationRequest request,
            String token) {

        token = token.substring(7);

        UUID userId = jwtService.extractUserId(token);

        CitizenResponse citizen =
                citizenFeignClient.getCitizenByUserId(userId);

        if (!Boolean.TRUE.equals(citizen.getPortalAccess())) {
            throw new BadRequestException(
                    "Portal access is disabled for this citizen.");
        }

        Application application = Application.builder()
                .applicationNumber(generateApplicationNumber())
                .citizenId(citizen.getId())
                .citizenUserId(citizen.getUserId())
                .planId(request.getPlanId())
                .status(ApplicationStatus.DRAFT)
                .build();

        Application saved =
                applicationRepository.save(application);

        return applicationMapper.toResponse(saved);
    }
    
    private String generateApplicationNumber() {
        return "APP-" + System.currentTimeMillis();
    }
}