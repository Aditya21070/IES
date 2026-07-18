package com.ies.citizen_service.service;

import java.util.List;
import java.util.UUID;

import com.ies.citizen_service.dto.CitizenRequest;
import com.ies.citizen_service.dto.CitizenResponse;

public interface CitizenService {

    CitizenResponse getCitizenById(Long id);

    List<CitizenResponse> getAllCitizens();
    
    CitizenResponse getLoggedInCitizen(String token);
    
    CitizenResponse selfRegisterCitizen(CitizenRequest request);

    CitizenResponse registerCitizenByCaseWorker(
            CitizenRequest request,
            String token);
    
    CitizenResponse getCitizenByUserId(UUID userId);
}