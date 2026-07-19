package com.ies.benefit_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.benefit_service.dto.BenefitResponse;
import com.ies.benefit_service.dto.DisburseBenefitRequest;
import com.ies.benefit_service.service.BenefitService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/benefits")
@RequiredArgsConstructor
public class BenefitController {

    private final BenefitService benefitService;

    @PostMapping("/disburse")
    public ResponseEntity<BenefitResponse> disburseBenefit(
            @Valid @RequestBody DisburseBenefitRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        benefitService.disburseBenefit(request)
                );
    }

}