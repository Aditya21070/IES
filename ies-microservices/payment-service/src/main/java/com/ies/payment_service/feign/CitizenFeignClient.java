package com.ies.payment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ies.payment_service.dto.feign.CitizenResponse;

@FeignClient(name = "citizen-service")
public interface CitizenFeignClient {

    @GetMapping("/citizens/{id}")
    CitizenResponse getCitizen(
            @PathVariable Long id);

}
