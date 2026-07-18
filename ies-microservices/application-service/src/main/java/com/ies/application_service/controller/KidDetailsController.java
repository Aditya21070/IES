package com.ies.application_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.application_service.dto.KidRequest;
import com.ies.application_service.dto.KidResponse;
import com.ies.application_service.service.KidDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kid-details")
@RequiredArgsConstructor
public class KidDetailsController {

    private final KidDetailsService kidDetailsService;

    @PostMapping
    public ResponseEntity<KidResponse> addKid(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody KidRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(kidDetailsService.addKid(request, token));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<List<KidResponse>> getKids(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                kidDetailsService.getKids(applicationId));
    }
}