package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.BankDetailsResponse;
import com.ies.citizen_service.entity.BankDetails;

@Component
public class BankDetailsMapper {

    public BankDetailsResponse toResponse(BankDetails bank) {

        return BankDetailsResponse.builder()
                .id(bank.getId())
                .applicationId(bank.getApplication().getId())
                .accountHolderName(bank.getAccountHolderName())
                .accountNumber(bank.getAccountNumber())
                .ifscCode(bank.getIfscCode())
                .bankName(bank.getBankName())
                .branch(bank.getBranch())
                .build();
    }
}
