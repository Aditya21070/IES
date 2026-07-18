package com.ies.application_service.service;

import com.ies.application_service.dto.EducationRequest;
import com.ies.application_service.dto.EducationResponse;

public interface EducationDetailsService {

    EducationResponse saveEducationDetails(
            EducationRequest request,
            String token);

    EducationResponse getEducationDetails(Long applicationId);

}