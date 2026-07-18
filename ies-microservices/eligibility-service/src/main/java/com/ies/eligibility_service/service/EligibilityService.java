package com.ies.eligibility_service.service;

import com.ies.eligibility_service.dto.EligibilityRequest;
import com.ies.eligibility_service.dto.EligibilityResponse;

public interface EligibilityService {

    EligibilityResponse evaluate(
            EligibilityRequest request);

    EligibilityResponse getResult(
            Long applicationId);

}