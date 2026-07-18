package com.ies.application_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.FamilyRequest;
import com.ies.application_service.dto.FamilyResponse;
import com.ies.application_service.service.FamilyDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/family-details")
@RequiredArgsConstructor
public class FamilyDetailsController {

    private final FamilyDetailsService familyDetailsService;

    @PostMapping
    public ResponseEntity<FamilyResponse> saveFamilyDetails(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody FamilyRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(familyDetailsService.saveFamilyDetails(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<FamilyResponse> getFamilyDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                familyDetailsService.getFamilyDetails(applicationId));
    }
}