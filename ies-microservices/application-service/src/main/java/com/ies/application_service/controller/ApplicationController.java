package com.ies.application_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;
import com.ies.application_service.dto.ApplicationSummaryResponse;
import com.ies.application_service.dto.UpdateApplicationStatusRequest;
import com.ies.application_service.dto.dashboard.ApplicationCountResponse;
import com.ies.application_service.dto.dashboard.ApplicationStatusCountResponse;
import com.ies.application_service.dto.dashboard.RecentApplicationsResponse;
import com.ies.application_service.enums.ApplicationStatus;
import com.ies.application_service.service.ApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.createApplication(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> getApplication(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                applicationService.getApplication(applicationId));
    }
    
    @PostMapping("/{applicationId}/submit")
    public ResponseEntity<ApplicationResponse> submitApplication(
            @PathVariable Long applicationId,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        return ResponseEntity.ok(
                applicationService.submitApplication(applicationId, token));
    }
    
    @GetMapping("/{id}/summary")
    public ResponseEntity<ApplicationSummaryResponse> getApplicationSummary(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationService.getApplicationSummary(id));
    }
    
    @PutMapping("/internal/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateApplicationStatusRequest request) {

        return ResponseEntity.ok(
                applicationService.updateStatus(
                        id,
                        request.getStatus()));
    }
    
    @GetMapping("/dashboard/count")
    public ResponseEntity<ApplicationCountResponse> getApplicationCount() {

        return ResponseEntity.ok(
                applicationService.getApplicationCount());
    }
    
    @GetMapping("/dashboard/status/{status}")
    public ResponseEntity<ApplicationStatusCountResponse> getStatusCount(
            @PathVariable ApplicationStatus status) {

        return ResponseEntity.ok(
                applicationService.getApplicationStatusCount(status));
    }
    
    @GetMapping("/dashboard/recent")
    public ResponseEntity<List<RecentApplicationsResponse>> getRecentApplications() {

        return ResponseEntity.ok(
                applicationService.getRecentApplications());
    }
}