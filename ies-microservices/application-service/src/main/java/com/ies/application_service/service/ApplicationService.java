package com.ies.application_service.service;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;
import com.ies.application_service.dto.ApplicationSummaryResponse;
import com.ies.application_service.enums.ApplicationStatus;

public interface ApplicationService {

    ApplicationResponse createApplication(
            ApplicationRequest request,
            String token);

    ApplicationResponse submitApplication(
            Long applicationId,
            String token);

    ApplicationResponse getApplication(Long applicationId);
    
    ApplicationSummaryResponse getApplicationSummary(Long applicationId);
    
    ApplicationResponse updateStatus(
            Long applicationId,
            ApplicationStatus status);
}