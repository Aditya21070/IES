package com.ies.application_service.service;

import com.ies.application_service.dto.FamilyRequest;
import com.ies.application_service.dto.FamilyResponse;

public interface FamilyDetailsService {

    FamilyResponse saveFamilyDetails(
            FamilyRequest request,
            String token);

    FamilyResponse getFamilyDetails(Long applicationId);

}