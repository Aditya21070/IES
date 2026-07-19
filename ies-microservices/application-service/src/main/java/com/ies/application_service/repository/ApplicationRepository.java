package com.ies.application_service.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.Application;
import com.ies.application_service.enums.ApplicationStatus;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByApplicationNumber(String applicationNumber);

    List<Application> findByCitizenId(Long citizenId);

    List<Application> findByCitizenUserId(UUID citizenUserId);

    boolean existsByApplicationNumber(String applicationNumber);

    long count();

    long countByStatus(ApplicationStatus status);

    List<Application> findTop10ByOrderByCreatedAtDesc();

}