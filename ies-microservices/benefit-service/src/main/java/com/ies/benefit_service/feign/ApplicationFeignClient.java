package com.ies.benefit_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ies.benefit_service.dto.feign.ApplicationResponse;
import com.ies.benefit_service.dto.feign.BankDetailsResponse;
import com.ies.benefit_service.dto.feign.UpdateApplicationStatusRequest;

@FeignClient(name = "application-service")
public interface ApplicationFeignClient {

    @GetMapping("/applications/{applicationId}")
    ApplicationResponse getApplication(
            @PathVariable Long applicationId);

    @GetMapping("/bank-details/{applicationId}")
    BankDetailsResponse getBankDetails(
            @PathVariable Long applicationId);

    @PutMapping("/applications/internal/{applicationId}/status")
    void updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestBody UpdateApplicationStatusRequest request);

}