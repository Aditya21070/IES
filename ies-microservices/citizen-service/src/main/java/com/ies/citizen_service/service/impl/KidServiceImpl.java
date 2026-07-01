package com.ies.citizen_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.KidRequest;
import com.ies.citizen_service.dto.KidResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.Kid;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.KidMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.KidRepository;
import com.ies.citizen_service.service.KidService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KidServiceImpl implements KidService {

    private final KidRepository kidRepository;
    private final ApplicationRepository applicationRepository;
    private final KidMapper kidMapper;

    @Override
    public KidResponse addKid(KidRequest request) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        Kid kid = Kid.builder()
                .application(application)
                .kidName(request.getKidName())
                .dob(request.getDob())
                .gender(request.getGender())
                .build();

        Kid saved = kidRepository.save(kid);

        return kidMapper.toResponse(saved);
    }

    @Override
    public List<KidResponse> getKids(Long applicationId) {

        return kidRepository.findByApplicationId(applicationId)
                .stream()
                .map(kidMapper::toResponse)
                .toList();
    }
}