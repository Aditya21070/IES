package com.ies.citizen_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.IncomeDetailsRequest;
import com.ies.citizen_service.dto.IncomeDetailsResponse;
import com.ies.citizen_service.service.IncomeDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/income-details")
@RequiredArgsConstructor
public class IncomeDetailsController {

    private final IncomeDetailsService incomeDetailsService;

    @PostMapping
    public ResponseEntity<IncomeDetailsResponse> saveIncomeDetails(
            @Valid @RequestBody IncomeDetailsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incomeDetailsService.saveIncomeDetails(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<IncomeDetailsResponse> getIncomeDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                incomeDetailsService.getIncomeDetails(applicationId)
        );
    }
}
