package com.ies.benefit_service.service;

import com.ies.benefit_service.dto.BenefitResponse;
import com.ies.benefit_service.dto.DisburseBenefitRequest;

public interface BenefitService {

    BenefitResponse disburseBenefit(
            DisburseBenefitRequest request);

}