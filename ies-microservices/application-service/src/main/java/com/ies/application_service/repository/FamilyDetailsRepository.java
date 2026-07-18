package com.ies.application_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.FamilyDetails;

public interface FamilyDetailsRepository
        extends JpaRepository<FamilyDetails, Long> {

    Optional<FamilyDetails> findByApplicationId(Long applicationId);

    boolean existsByApplicationId(Long applicationId);

}