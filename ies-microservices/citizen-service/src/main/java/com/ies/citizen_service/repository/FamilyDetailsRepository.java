package com.ies.citizen_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.citizen_service.entity.FamilyDetails;

public interface FamilyDetailsRepository extends JpaRepository<FamilyDetails, Long>{

    Optional<FamilyDetails> findByApplicationId(Long applicationId);

}
