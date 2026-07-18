package com.ies.plan_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ies.plan_service.dto.PlanRequest;
import com.ies.plan_service.dto.PlanResponse;
import com.ies.plan_service.service.PlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(
            @Valid @RequestBody PlanRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(planService.createPlan(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponse> getPlanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                planService.getPlanById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans() {

        return ResponseEntity.ok(
                planService.getAllPlans());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PlanResponse>> getActivePlans() {

        return ResponseEntity.ok(
                planService.getActivePlans());
    }
}