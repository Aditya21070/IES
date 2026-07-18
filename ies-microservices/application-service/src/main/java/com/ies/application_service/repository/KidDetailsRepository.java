package com.ies.application_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.KidDetails;

public interface KidDetailsRepository
        extends JpaRepository<KidDetails, Long> {

    List<KidDetails> findByApplicationId(Long applicationId);

    boolean existsByAadhaarNumber(String aadhaarNumber);

    long countByApplicationId(Long applicationId);
    
    List<KidDetails> findAllByApplicationId(Long applicationId);
}