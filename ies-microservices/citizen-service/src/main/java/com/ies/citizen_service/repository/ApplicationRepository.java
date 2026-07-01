package com.ies.citizen_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.citizen_service.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Optional<Application> findByApplicationNumber(String applicationNumber);

    List<Application> findByCitizenId(Long citizenId);
}
