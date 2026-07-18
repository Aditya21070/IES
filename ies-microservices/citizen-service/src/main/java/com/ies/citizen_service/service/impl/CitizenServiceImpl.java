package com.ies.citizen_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.CitizenRequest;
import com.ies.citizen_service.dto.CitizenResponse;
import com.ies.citizen_service.dto.CitizenUserRequest;
import com.ies.citizen_service.entity.Citizen;
import com.ies.citizen_service.exception.BadRequestException;
import com.ies.citizen_service.exception.DuplicateResourceException;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.feign.AuthFeignClient;
import com.ies.citizen_service.mapper.CitizenMapper;
import com.ies.citizen_service.repository.CitizenRepository;
import com.ies.citizen_service.security.JwtService;
import com.ies.citizen_service.service.CitizenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
	
	private final CitizenMapper citizenMapper;

    private final CitizenRepository citizenRepository;
    
    private final AuthFeignClient authFeignClient;
    
    private final JwtService jwtService;

    private String generateCitizenNumber() {

        long count = citizenRepository.count() + 1;

        return String.format("CIT%06d", count);
    }
    
    @Override
    public CitizenResponse getCitizenById(Long id) {

        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found"));

        return citizenMapper.toResponse(citizen);
    }
    
    @Override
    public List<CitizenResponse> getAllCitizens() {

	    	return citizenRepository.findAll()
	    	        .stream()
	    	        .map(citizenMapper::toResponse)
	    	        .toList();	
    }
    
    @Override
    public CitizenResponse getLoggedInCitizen(String token) {

        UUID userId = jwtService.extractUserId(token);

        Citizen citizen = citizenRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found"));

        return citizenMapper.toResponse(citizen);
    }
    
    @Override
    public CitizenResponse selfRegisterCitizen(CitizenRequest request) {

        request.setPortalAccess(true);

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new BadRequestException("Password is required");
        }

        validateCitizen(request);

        Citizen citizen = citizenMapper.toEntity(request);

        citizen.setCitizenNumber(generateCitizenNumber());

        CitizenUserRequest authRequest = new CitizenUserRequest();

        authRequest.setName(request.getFullName());
        authRequest.setEmail(request.getEmail());
        authRequest.setPassword(request.getPassword());
        authRequest.setPhoneNumber(request.getPhoneNumber());

        UUID userId = authFeignClient.createCitizenUser(authRequest);

        citizen.setUserId(userId);

        Citizen savedCitizen = citizenRepository.save(citizen);

        return citizenMapper.toResponse(savedCitizen);
    }
    
    @Override
    public CitizenResponse registerCitizenByCaseWorker(
            CitizenRequest request,
            String token) {

        validateCitizen(request);

        Citizen citizen = citizenMapper.toEntity(request);

        citizen.setCitizenNumber(generateCitizenNumber());
        
        UUID createdBy = jwtService.extractUserId(token);

        citizen.setCreatedBy(createdBy);

        if (Boolean.TRUE.equals(request.getPortalAccess())) {

            if (request.getEmail() == null || request.getEmail().isBlank()) {
                throw new BadRequestException("Email is required");
            }

            if (request.getPassword() == null || request.getPassword().isBlank()) {
                throw new BadRequestException("Password is required");
            }

            CitizenUserRequest authRequest = new CitizenUserRequest();

            authRequest.setName(request.getFullName());
            authRequest.setEmail(request.getEmail());
            authRequest.setPassword(request.getPassword());
            authRequest.setPhoneNumber(request.getPhoneNumber());

            UUID userId = authFeignClient.createCitizenUser(authRequest);

            citizen.setUserId(userId);
        }

        Citizen savedCitizen = citizenRepository.save(citizen);

        return citizenMapper.toResponse(savedCitizen);
    }
    
    private void validateCitizen(CitizenRequest request) {

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && citizenRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException("Email already exists");
        }

        if (citizenRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        if (citizenRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {
            throw new DuplicateResourceException("Aadhaar number already exists");
        }
    }
    
    @Override
    public CitizenResponse getCitizenByUserId(UUID userId) {

        Citizen citizen = citizenRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Citizen not found"));

        return citizenMapper.toResponse(citizen);
    }
}
