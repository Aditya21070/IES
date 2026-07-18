package com.ies.application_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.application_service.dto.BankRequest;
import com.ies.application_service.dto.BankResponse;
import com.ies.application_service.entity.BankDetails;

@Component
public class BankMapper {

    public BankDetails toEntity(BankRequest request) {

        return BankDetails.builder()
                .applicationId(request.getApplicationId())
                .bankName(request.getBankName())
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .ifscCode(request.getIfscCode())
                .branchName(request.getBranchName())
                .build();
    }

    public BankResponse toResponse(BankDetails bank) {

        return BankResponse.builder()
                .id(bank.getId())
                .applicationId(bank.getApplicationId())
                .bankName(bank.getBankName())
                .accountHolderName(bank.getAccountHolderName())
                .accountNumber(bank.getAccountNumber())
                .ifscCode(bank.getIfscCode())
                .branchName(bank.getBranchName())
                .createdAt(bank.getCreatedAt())
                .build();
    }
}