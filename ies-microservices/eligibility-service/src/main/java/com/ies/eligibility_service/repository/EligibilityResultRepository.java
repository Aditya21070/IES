package com.ies.eligibility_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.eligibility_service.entity.EligibilityResult;

public interface EligibilityResultRepository
        extends JpaRepository<EligibilityResult, Long> {

    Optional<EligibilityResult> findByApplicationId(Long applicationId);

    List<EligibilityResult> findByCitizenId(Long citizenId);

    boolean existsByApplicationId(Long applicationId);
}