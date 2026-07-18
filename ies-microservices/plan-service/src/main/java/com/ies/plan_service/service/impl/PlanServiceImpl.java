package com.ies.plan_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.plan_service.dto.PlanRequest;
import com.ies.plan_service.dto.PlanResponse;
import com.ies.plan_service.entity.Plan;
import com.ies.plan_service.exception.DuplicateResourceException;
import com.ies.plan_service.exception.ResourceNotFoundException;
import com.ies.plan_service.mapper.PlanMapper;
import com.ies.plan_service.repository.PlanRepository;
import com.ies.plan_service.service.PlanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    @Override
    public PlanResponse createPlan(PlanRequest request) {

        if (planRepository.existsByPlanName(request.getPlanName())) {
            throw new DuplicateResourceException(
                    "Plan already exists.");
        }

        validatePlan(request);

        Plan plan = planMapper.toEntity(request);

        Plan savedPlan = planRepository.save(plan);

        return planMapper.toResponse(savedPlan);
    }

    @Override
    public PlanResponse getPlanById(Long id) {

        Plan plan = planRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found."));

        return planMapper.toResponse(plan);
    }

    @Override
    public List<PlanResponse> getAllPlans() {

        return planRepository.findByDeletedFalse()
                .stream()
                .map(planMapper::toResponse)
                .toList();
    }

    @Override
    public List<PlanResponse> getActivePlans() {

        return planRepository.findByActiveTrueAndDeletedFalse()
                .stream()
                .map(planMapper::toResponse)
                .toList();
    }

    private void validatePlan(PlanRequest request) {

        if (request.getMinAge() != null
                && request.getMaxAge() != null
                && request.getMinAge() > request.getMaxAge()) {

            throw new IllegalArgumentException(
                    "Minimum age cannot be greater than maximum age.");
        }

        if (request.getMaxChildren() != null
                && request.getMaxChildren() < 0) {

            throw new IllegalArgumentException(
                    "Maximum children cannot be negative.");
        }
    }
    
    @Override
    public PlanResponse updatePlan(
            Long id,
            PlanRequest request) {

        Plan plan = planRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found."));

        if (!plan.getPlanName().equalsIgnoreCase(request.getPlanName())
                && planRepository.existsByPlanName(request.getPlanName())) {

            throw new DuplicateResourceException(
                    "Plan name already exists.");
        }

        validatePlan(request);

        plan.setPlanName(request.getPlanName());
        plan.setPlanCategory(request.getPlanCategory());
        plan.setDescription(request.getDescription());
        plan.setBenefitAmount(request.getBenefitAmount());
        plan.setMinAge(request.getMinAge());
        plan.setMaxAge(request.getMaxAge());
        plan.setIncomeLimit(request.getIncomeLimit());
        plan.setEmploymentRequired(request.getEmploymentRequired());
        plan.setMaxChildren(request.getMaxChildren());

        if (request.getActive() != null) {
            plan.setActive(request.getActive());
        }

        Plan updatedPlan = planRepository.save(plan);

        return planMapper.toResponse(updatedPlan);
    }
    
    @Override
    public PlanResponse activatePlan(Long id) {

        Plan plan = planRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found."));

        plan.setActive(true);

        return planMapper.toResponse(
                planRepository.save(plan));
    }
    
    @Override
    public PlanResponse deactivatePlan(Long id) {

        Plan plan = planRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found."));

        plan.setActive(false);

        return planMapper.toResponse(
                planRepository.save(plan));
    }
    
    @Override
    public void deletePlan(Long id) {

        Plan plan = planRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found."));

        plan.setDeleted(true);

        planRepository.save(plan);
    }
}