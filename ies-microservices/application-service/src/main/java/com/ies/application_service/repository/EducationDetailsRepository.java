package com.ies.application_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.EducationDetails;

public interface EducationDetailsRepository
        extends JpaRepository<EducationDetails, Long> {

    Optional<EducationDetails> findByApplicationId(Long applicationId);

    boolean existsByApplicationId(Long applicationId);

}