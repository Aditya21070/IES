package com.ies.application_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.EducationRequest;
import com.ies.application_service.dto.EducationResponse;
import com.ies.application_service.service.EducationDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/education-details")
@RequiredArgsConstructor
public class EducationDetailsController {

    private final EducationDetailsService educationDetailsService;

    @PostMapping
    public ResponseEntity<EducationResponse> saveEducationDetails(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody EducationRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(educationDetailsService.saveEducationDetails(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<EducationResponse> getEducationDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                educationDetailsService.getEducationDetails(applicationId));
    }
}