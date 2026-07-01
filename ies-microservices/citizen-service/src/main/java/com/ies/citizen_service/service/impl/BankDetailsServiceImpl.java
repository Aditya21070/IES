package com.ies.citizen_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.BankDetailsRequest;
import com.ies.citizen_service.dto.BankDetailsResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.BankDetails;
import com.ies.citizen_service.exception.DuplicateResourceException;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.BankDetailsMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.BankDetailsRepository;
import com.ies.citizen_service.service.BankDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;
    private final ApplicationRepository applicationRepository;
    private final BankDetailsMapper bankDetailsMapper;

    @Override
    public BankDetailsResponse saveBankDetails(BankDetailsRequest request) {

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (bankDetailsRepository.findByApplicationId(request.getApplicationId()).isPresent()) {
            throw new DuplicateResourceException("Bank details already exist");
        }

        BankDetails bankDetails = BankDetails.builder()
                .application(application)
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .ifscCode(request.getIfscCode())
                .bankName(request.getBankName())
                .branch(request.getBranch())
                .build();

        BankDetails saved = bankDetailsRepository.save(bankDetails);

        return bankDetailsMapper.toResponse(saved);
    }

    @Override
    public BankDetailsResponse getBankDetails(Long applicationId) {

        BankDetails bankDetails = bankDetailsRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found"));

        return bankDetailsMapper.toResponse(bankDetails);
    }
}
