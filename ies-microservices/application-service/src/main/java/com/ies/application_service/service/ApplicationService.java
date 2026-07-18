package com.ies.application_service.service;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;

public interface ApplicationService {

    ApplicationResponse createApplication(
            ApplicationRequest request,
            String token);

    ApplicationResponse submitApplication(
            Long applicationId,
            String token);

    ApplicationResponse getApplication(Long applicationId);
}