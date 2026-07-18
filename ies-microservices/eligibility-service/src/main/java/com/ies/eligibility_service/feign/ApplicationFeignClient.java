package com.ies.eligibility_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ies.eligibility_service.dto.feign.ApplicationResponse;
import com.ies.eligibility_service.dto.feign.ApplicationSummaryResponse;
import com.ies.eligibility_service.dto.feign.UpdateApplicationStatusRequest;

@FeignClient(name = "application-service")
public interface ApplicationFeignClient {

    @GetMapping("/applications/{id}/summary")
    ApplicationSummaryResponse getApplicationSummary(
            @PathVariable Long id);
    
    @PutMapping("/applications/{id}/status")
    ApplicationResponse updateApplicationStatus(
            @PathVariable Long id,
            @RequestBody UpdateApplicationStatusRequest request);
}