package com.ies.citizen_service.service;

import java.util.List;

import com.ies.citizen_service.dto.ApplicationRequest;
import com.ies.citizen_service.dto.ApplicationResponse;

public interface ApplicationService {

    ApplicationResponse createApplication(ApplicationRequest request);

    List<ApplicationResponse> getApplicationsByCitizen(Long citizenId);

    ApplicationResponse getApplication(Long id);
    
    ApplicationResponse submitApplication(Long applicationId);
}
