package com.ies.application_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.IncomeDetails;

public interface IncomeDetailsRepository
        extends JpaRepository<IncomeDetails, Long> {

    Optional<IncomeDetails> findByApplicationId(Long applicationId);

    boolean existsByApplicationId(Long applicationId);
}