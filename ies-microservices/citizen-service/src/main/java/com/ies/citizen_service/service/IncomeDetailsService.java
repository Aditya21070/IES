package com.ies.citizen_service.service;

import com.ies.citizen_service.dto.IncomeDetailsRequest;
import com.ies.citizen_service.dto.IncomeDetailsResponse;

public interface IncomeDetailsService {

    IncomeDetailsResponse saveIncomeDetails(IncomeDetailsRequest request);

    IncomeDetailsResponse getIncomeDetails(Long applicationId);

}
