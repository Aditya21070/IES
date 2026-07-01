package com.ies.citizen_service.service;

import com.ies.citizen_service.dto.BankDetailsRequest;
import com.ies.citizen_service.dto.BankDetailsResponse;

public interface BankDetailsService {

    BankDetailsResponse saveBankDetails(BankDetailsRequest request);

    BankDetailsResponse getBankDetails(Long applicationId);

}
