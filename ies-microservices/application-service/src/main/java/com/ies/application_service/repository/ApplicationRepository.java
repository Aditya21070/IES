package com.ies.application_service.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.application_service.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByApplicationNumber(String applicationNumber);

    List<Application> findByCitizenId(Long citizenId);

    List<Application> findByCitizenUserId(UUID citizenUserId);

    boolean existsByApplicationNumber(String applicationNumber);

}