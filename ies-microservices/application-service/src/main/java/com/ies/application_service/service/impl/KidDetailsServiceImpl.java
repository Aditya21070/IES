package com.ies.application_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.KidRequest;
import com.ies.application_service.dto.KidResponse;
import com.ies.application_service.entity.KidDetails;
import com.ies.application_service.exceptions.DuplicateResourceException;
import com.ies.application_service.mapper.KidMapper;
import com.ies.application_service.repository.KidDetailsRepository;
import com.ies.application_service.service.KidDetailsService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KidDetailsServiceImpl
        implements KidDetailsService {

    private final KidDetailsRepository kidRepository;

    private final KidMapper kidMapper;

    private final ApplicationSecurityService applicationSecurityService;

    @Override
    public KidResponse addKid(
            KidRequest request,
            String token) {

        applicationSecurityService.validateOwnership(
                request.getApplicationId(),
                token);

        if (request.getAadhaarNumber() != null
                && !request.getAadhaarNumber().isBlank()
                && kidRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {

            throw new DuplicateResourceException(
                    "Aadhaar number already exists.");
        }

        KidDetails kid = kidMapper.toEntity(request);

        KidDetails savedKid = kidRepository.save(kid);

        return kidMapper.toResponse(savedKid);
    }

    @Override
    public List<KidResponse> getKids(Long applicationId) {

        return kidRepository.findByApplicationId(applicationId)
                .stream()
                .map(kidMapper::toResponse)
                .toList();
    }
}