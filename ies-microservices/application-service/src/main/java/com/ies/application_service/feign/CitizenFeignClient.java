package com.ies.application_service.feign;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ies.application_service.config.FeignConfig;
import com.ies.application_service.dto.feign.CitizenResponse;

@FeignClient(
	    name = "citizen-service",
	    configuration = FeignConfig.class
	)
public interface CitizenFeignClient {

    @GetMapping("/citizens/user/{userId}")
    CitizenResponse getCitizenByUserId(
            @PathVariable UUID userId);

}