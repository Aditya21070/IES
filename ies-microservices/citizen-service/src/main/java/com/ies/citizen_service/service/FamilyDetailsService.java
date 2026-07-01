package com.ies.citizen_service.service;

import com.ies.citizen_service.dto.FamilyDetailsRequest;
import com.ies.citizen_service.dto.FamilyDetailsResponse;

public interface FamilyDetailsService {

    FamilyDetailsResponse saveFamilyDetails(FamilyDetailsRequest request);

    FamilyDetailsResponse getFamilyDetails(Long applicationId);

}