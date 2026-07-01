package com.ies.citizen_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.citizen_service.entity.Kid;

@Repository
public interface KidRepository extends JpaRepository<Kid, Long> {

    List<Kid> findByApplicationId(Long applicationId);

}
