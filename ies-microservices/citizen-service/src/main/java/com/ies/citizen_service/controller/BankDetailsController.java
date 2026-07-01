package com.ies.citizen_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.BankDetailsRequest;
import com.ies.citizen_service.dto.BankDetailsResponse;
import com.ies.citizen_service.service.BankDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank-details")
@RequiredArgsConstructor
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    @PostMapping
    public ResponseEntity<BankDetailsResponse> saveBankDetails(
            @Valid @RequestBody BankDetailsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankDetailsService.saveBankDetails(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<BankDetailsResponse> getBankDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                bankDetailsService.getBankDetails(applicationId));
    }
}
