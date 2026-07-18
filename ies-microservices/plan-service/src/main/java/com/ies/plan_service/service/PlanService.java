package com.ies.plan_service.service;

import java.util.List;

import com.ies.plan_service.dto.PlanRequest;
import com.ies.plan_service.dto.PlanResponse;

public interface PlanService {

    PlanResponse createPlan(PlanRequest request);

    PlanResponse getPlanById(Long id);

    List<PlanResponse> getAllPlans();

    List<PlanResponse> getActivePlans();

    PlanResponse updatePlan(
            Long id,
            PlanRequest request);
    
    PlanResponse activatePlan(Long id);

    PlanResponse deactivatePlan(Long id);

    void deletePlan(Long id);
}