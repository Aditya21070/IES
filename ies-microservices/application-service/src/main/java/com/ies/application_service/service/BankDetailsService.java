package com.ies.application_service.service;

import com.ies.application_service.dto.BankRequest;
import com.ies.application_service.dto.BankResponse;

public interface BankDetailsService {

    BankResponse saveBankDetails(
            BankRequest request,
            String token);

    BankResponse getBankDetails(Long applicationId);

}