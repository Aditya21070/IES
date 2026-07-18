package com.ies.application_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.BankDetails;

public interface BankDetailsRepository
        extends JpaRepository<BankDetails, Long> {

    Optional<BankDetails> findByApplicationId(Long applicationId);

    boolean existsByApplicationId(Long applicationId);

    boolean existsByAccountNumber(String accountNumber);

}