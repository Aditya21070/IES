package com.ies.benefit_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.benefit_service.entity.BenefitTransaction;

public interface BenefitRepository
        extends JpaRepository<BenefitTransaction, Long> {

    boolean existsByApplicationId(Long applicationId);

    Optional<BenefitTransaction> findByApplicationId(Long applicationId);

}