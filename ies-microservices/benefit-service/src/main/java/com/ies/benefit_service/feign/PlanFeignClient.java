package com.ies.benefit_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ies.benefit_service.dto.feign.PlanResponse;

@FeignClient(name = "plan-service")
public interface PlanFeignClient {

    @GetMapping("/plans/{planId}")
    PlanResponse getPlan(
            @PathVariable Long planId);

}