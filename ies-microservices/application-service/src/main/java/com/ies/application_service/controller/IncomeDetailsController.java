package com.ies.application_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.IncomeRequest;
import com.ies.application_service.dto.IncomeResponse;
import com.ies.application_service.service.IncomeDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/income-details")
@RequiredArgsConstructor
public class IncomeDetailsController {

    private final IncomeDetailsService incomeDetailsService;

    @PostMapping
    public ResponseEntity<IncomeResponse> saveIncomeDetails(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody IncomeRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incomeDetailsService.saveIncomeDetails(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<IncomeResponse> getIncomeDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                incomeDetailsService.getIncomeDetails(applicationId));
    }
}