package com.ies.citizen_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.CitizenRequest;
import com.ies.citizen_service.dto.CitizenResponse;
import com.ies.citizen_service.service.CitizenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/citizens")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping("/register")
    public ResponseEntity<CitizenResponse> selfRegisterCitizen(
            @Valid @RequestBody CitizenRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citizenService.selfRegisterCitizen(request));
    }

    @PostMapping
    public ResponseEntity<CitizenResponse> registerCitizenByCaseWorker(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody CitizenRequest request) {

        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citizenService.registerCitizenByCaseWorker(request, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponse> getCitizen(@PathVariable Long id) {

        return ResponseEntity.ok(citizenService.getCitizenById(id));
    }

    @GetMapping
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {

        return ResponseEntity.ok(citizenService.getAllCitizens());
    }
    
    @GetMapping("/me")
    public ResponseEntity<CitizenResponse> getMyProfile(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        return ResponseEntity.ok(citizenService.getLoggedInCitizen(token));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<CitizenResponse> getCitizenByUserId(
            @PathVariable UUID userId) {

        return ResponseEntity.ok(
                citizenService.getCitizenByUserId(userId));
    }
}
