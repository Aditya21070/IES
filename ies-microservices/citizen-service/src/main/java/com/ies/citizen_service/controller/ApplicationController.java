package com.ies.citizen_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.ApplicationRequest;
import com.ies.citizen_service.dto.ApplicationResponse;
import com.ies.citizen_service.service.ApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.createApplication(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplication(
            @PathVariable Long id) {

        return ResponseEntity.ok(applicationService.getApplication(id));
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByCitizen(
            @PathVariable Long citizenId) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByCitizen(citizenId)
        );
    }
    
    @PutMapping("/{applicationId}/submit")
    public ResponseEntity<ApplicationResponse> submitApplication(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                applicationService.submitApplication(applicationId)
        );
    }
}
