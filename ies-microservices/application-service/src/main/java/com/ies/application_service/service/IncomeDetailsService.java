package com.ies.application_service.service;

import com.ies.application_service.dto.IncomeRequest;
import com.ies.application_service.dto.IncomeResponse;

public interface IncomeDetailsService {

	IncomeResponse saveIncomeDetails(
	        IncomeRequest request,
	        String token);

    IncomeResponse getIncomeDetails(Long applicationId);

}