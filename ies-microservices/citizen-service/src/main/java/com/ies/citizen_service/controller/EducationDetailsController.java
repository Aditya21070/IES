package com.ies.citizen_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.EducationDetailsRequest;
import com.ies.citizen_service.dto.EducationDetailsResponse;
import com.ies.citizen_service.service.EducationDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/education-details")
@RequiredArgsConstructor
public class EducationDetailsController {

    private final EducationDetailsService educationDetailsService;

    @PostMapping
    public ResponseEntity<EducationDetailsResponse> saveEducationDetails(
            @Valid @RequestBody EducationDetailsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(educationDetailsService.saveEducationDetails(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<EducationDetailsResponse> getEducationDetails(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                educationDetailsService.getEducationDetails(applicationId)
        );
    }
}
