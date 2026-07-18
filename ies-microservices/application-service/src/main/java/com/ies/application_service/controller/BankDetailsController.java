package com.ies.application_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.BankRequest;
import com.ies.application_service.dto.BankResponse;
import com.ies.application_service.service.BankDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank-details")
@RequiredArgsConstructor
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    @PostMapping
    public ResponseEntity<BankResponse> saveBankDetails(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody BankRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankDetailsService.saveBankDetails(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<BankResponse> getBankDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                bankDetailsService.getBankDetails(applicationId));
    }
}