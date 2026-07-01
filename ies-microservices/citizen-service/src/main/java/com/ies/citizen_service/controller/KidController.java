package com.ies.citizen_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.citizen_service.dto.KidRequest;
import com.ies.citizen_service.dto.KidResponse;
import com.ies.citizen_service.service.KidService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kids")
@RequiredArgsConstructor
public class KidController {

    private final KidService kidService;

    @PostMapping
    public ResponseEntity<KidResponse> addKid(
            @Valid @RequestBody KidRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(kidService.addKid(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<List<KidResponse>> getKids(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(kidService.getKids(applicationId));
    }
}
