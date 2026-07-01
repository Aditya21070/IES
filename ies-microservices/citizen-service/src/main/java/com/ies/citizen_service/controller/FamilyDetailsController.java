package com.ies.citizen_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.FamilyDetailsRequest;
import com.ies.citizen_service.dto.FamilyDetailsResponse;
import com.ies.citizen_service.service.FamilyDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/family-details")
@RequiredArgsConstructor
public class FamilyDetailsController {

    private final FamilyDetailsService familyDetailsService;

    @PostMapping
    public ResponseEntity<FamilyDetailsResponse> saveFamilyDetails(
            @Valid @RequestBody FamilyDetailsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(familyDetailsService.saveFamilyDetails(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<FamilyDetailsResponse> getFamilyDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                familyDetailsService.getFamilyDetails(applicationId));
    }

}
