package com.ies.eligibility_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.eligibility_service.dto.EligibilityRequest;
import com.ies.eligibility_service.dto.EligibilityResponse;
import com.ies.eligibility_service.service.EligibilityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eligibility")
@RequiredArgsConstructor
public class EligibilityController {

    private final EligibilityService eligibilityService;

    @PostMapping("/evaluate")
    public ResponseEntity<EligibilityResponse> evaluate(
            @Valid @RequestBody EligibilityRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eligibilityService.evaluate(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<EligibilityResponse> getResult(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                eligibilityService.getResult(applicationId));
    }
}