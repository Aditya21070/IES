package com.ies.citizen_service.service;

import com.ies.citizen_service.dto.EducationDetailsRequest;
import com.ies.citizen_service.dto.EducationDetailsResponse;

public interface EducationDetailsService {

    EducationDetailsResponse saveEducationDetails(EducationDetailsRequest request);

    EducationDetailsResponse getEducationDetails(Long applicationId);
}
