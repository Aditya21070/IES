package com.ies.citizen_service.feign;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ies.citizen_service.dto.CitizenUserRequest;

@FeignClient(name = "auth-service")
public interface AuthFeignClient {

    @PostMapping("/auth/citizen")
    UUID createCitizenUser(@RequestBody CitizenUserRequest request);

}