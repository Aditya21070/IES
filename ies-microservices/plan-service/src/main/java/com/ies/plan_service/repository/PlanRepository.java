package com.ies.plan_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.plan_service.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByPlanName(String planName);

    boolean existsByPlanName(String planName);

    List<Plan> findByActiveTrueAndDeletedFalse();

    List<Plan> findByDeletedFalse();

}