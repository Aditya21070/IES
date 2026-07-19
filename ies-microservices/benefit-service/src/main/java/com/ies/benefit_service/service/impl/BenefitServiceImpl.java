package com.ies.benefit_service.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ies.benefit_service.dto.BenefitResponse;
import com.ies.benefit_service.dto.DisburseBenefitRequest;
import com.ies.benefit_service.dto.feign.ApplicationResponse;
import com.ies.benefit_service.dto.feign.BankDetailsResponse;
import com.ies.benefit_service.dto.feign.PlanResponse;
import com.ies.benefit_service.dto.feign.UpdateApplicationStatusRequest;
import com.ies.benefit_service.entity.BenefitTransaction;
import com.ies.benefit_service.enums.ApplicationStatus;
import com.ies.benefit_service.enums.BenefitStatus;
import com.ies.benefit_service.feign.ApplicationFeignClient;
import com.ies.benefit_service.feign.PlanFeignClient;
import com.ies.benefit_service.mapper.BenefitMapper;
import com.ies.benefit_service.repository.BenefitRepository;
import com.ies.benefit_service.service.BenefitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitRepository benefitRepository;

    private final BenefitMapper benefitMapper;

    private final ApplicationFeignClient applicationFeignClient;

    private final PlanFeignClient planFeignClient;

    @Override
    public BenefitResponse disburseBenefit(
            DisburseBenefitRequest request) {

        // Prevent duplicate benefit disbursement
        if (benefitRepository.existsByApplicationId(
                request.getApplicationId())) {

            throw new RuntimeException(
                    "Benefit already disbursed for this application.");
        }

        // Fetch Application
        ApplicationResponse application =
                applicationFeignClient.getApplication(
                        request.getApplicationId());

        // Verify payment completed
        if (application.getStatus()
                != ApplicationStatus.PAYMENT_COMPLETED) {

            throw new RuntimeException(
                    "Application payment is not completed.");
        }

        // Fetch Plan
        PlanResponse plan =
                planFeignClient.getPlan(
                        application.getPlanId());

        // Fetch Bank Details
        BankDetailsResponse bank =
                applicationFeignClient.getBankDetails(
                        application.getId());

        /*
         * In a real system this is where
         * payment gateway / bank integration
         * would happen.
         *
         * For now we simulate successful
         * government benefit transfer.
         */

        BenefitTransaction benefit =
                BenefitTransaction.builder()
                        .applicationId(application.getId())
                        .citizenId(application.getCitizenId())
                        .planId(plan.getId())
                        .benefitAmount(plan.getBenefitAmount())
                        .transactionReference(
                                "BEN-" + System.currentTimeMillis())
                        .benefitStatus(BenefitStatus.PROCESSING)
                        .build();

        benefit = benefitRepository.save(benefit);

        // Simulate transfer success
        benefit.setBenefitStatus(BenefitStatus.SENT);
        benefit.setSentAt(LocalDateTime.now());

        benefit = benefitRepository.save(benefit);

        // Update Application Status
        applicationFeignClient.updateApplicationStatus(

                application.getId(),

                UpdateApplicationStatusRequest
                        .builder()
                        .status(ApplicationStatus.BENEFIT_SENT)
                        .build());

        return benefitMapper.toResponse(benefit);
    }

}