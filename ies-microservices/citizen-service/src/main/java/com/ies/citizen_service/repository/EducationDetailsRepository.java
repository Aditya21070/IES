package com.ies.citizen_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.citizen_service.entity.EducationDetails;

@Repository
public interface EducationDetailsRepository
        extends JpaRepository<EducationDetails, Long> {

    Optional<EducationDetails> findByApplicationId(Long applicationId);

}
