package com.ies.application_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;
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

}