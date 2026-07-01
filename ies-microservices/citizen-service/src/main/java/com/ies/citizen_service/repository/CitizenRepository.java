package com.ies.citizen_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.citizen_service.entity.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	Optional<Citizen> findByCitizenNumber(String citizenNumber);

    Optional<Citizen> findByEmail(String email);

    Optional<Citizen> findByPhoneNumber(String phoneNumber);

    Optional<Citizen> findByAadhaarNumber(String aadhaarNumber);
    
    Optional<Citizen> findByUserId(UUID userId);
    
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);
}
