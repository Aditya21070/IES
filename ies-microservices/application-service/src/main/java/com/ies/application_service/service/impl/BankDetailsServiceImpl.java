package com.ies.application_service.service.impl;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.BankRequest;
import com.ies.application_service.dto.BankResponse;
import com.ies.application_service.entity.BankDetails;
import com.ies.application_service.exceptions.DuplicateResourceException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.mapper.BankMapper;
import com.ies.application_service.repository.BankDetailsRepository;
import com.ies.application_service.service.BankDetailsService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsRepository bankRepository;

    private final BankMapper bankMapper;

    private final ApplicationSecurityService applicationSecurityService;

    @Override
    public BankResponse saveBankDetails(
            BankRequest request,
            String token) {

        // Verify citizen owns the application
        applicationSecurityService.validateOwnership(
                request.getApplicationId(),
                token);

        // One bank record per application
        if (bankRepository.existsByApplicationId(request.getApplicationId())) {
            throw new DuplicateResourceException(
                    "Bank details already exist for this application.");
        }

        // Prevent duplicate account numbers
        if (bankRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new DuplicateResourceException(
                    "Account number already exists.");
        }

        BankDetails bank = bankMapper.toEntity(request);

        BankDetails savedBank = bankRepository.save(bank);

        return bankMapper.toResponse(savedBank);
    }

    @Override
    public BankResponse getBankDetails(Long applicationId) {

        BankDetails bank = bankRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bank details not found."));

        return bankMapper.toResponse(bank);
    }
}