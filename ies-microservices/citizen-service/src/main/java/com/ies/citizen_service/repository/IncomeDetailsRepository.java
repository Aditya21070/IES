package com.ies.citizen_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.citizen_service.entity.IncomeDetails;

@Repository
public interface IncomeDetailsRepository
        extends JpaRepository<IncomeDetails, Long> {

    Optional<IncomeDetails> findByApplicationId(Long applicationId);

}
