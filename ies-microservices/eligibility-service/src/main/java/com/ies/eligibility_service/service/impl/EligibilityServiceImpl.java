package com.ies.eligibility_service.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.eligibility_service.dto.EligibilityRequest;
import com.ies.eligibility_service.dto.EligibilityResponse;
import com.ies.eligibility_service.dto.feign.ApplicationSummaryResponse;
import com.ies.eligibility_service.dto.feign.CitizenResponse;
import com.ies.eligibility_service.dto.feign.PlanResponse;
import com.ies.eligibility_service.dto.feign.UpdateApplicationStatusRequest;
import com.ies.eligibility_service.entity.EligibilityResult;
import com.ies.eligibility_service.enums.ApplicationStatus;
import com.ies.eligibility_service.enums.EligibilityStatus;
import com.ies.eligibility_service.enums.EmploymentStatus;
import com.ies.eligibility_service.exception.DuplicateResourceException;
import com.ies.eligibility_service.exception.ResourceNotFoundException;
import com.ies.eligibility_service.feign.ApplicationFeignClient;
import com.ies.eligibility_service.feign.CitizenFeignClient;
import com.ies.eligibility_service.feign.PlanFeignClient;
import com.ies.eligibility_service.mapper.EligibilityMapper;
import com.ies.eligibility_service.repository.EligibilityResultRepository;
import com.ies.eligibility_service.service.EligibilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EligibilityServiceImpl
        implements EligibilityService {

    private final EligibilityResultRepository eligibilityRepository;

    private final EligibilityMapper eligibilityMapper;

    private final ApplicationFeignClient applicationFeignClient;

    private final CitizenFeignClient citizenFeignClient;

    private final PlanFeignClient planFeignClient;

    @Override
    public EligibilityResponse evaluate(
            EligibilityRequest request) {
	    	if (eligibilityRepository.existsByApplicationId(request.getApplicationId())) {
	
	    	    throw new DuplicateResourceException(
	    	            "Application has already been evaluated.");
	    	}
	    	
	    	ApplicationSummaryResponse summary =
	    	        applicationFeignClient.getApplicationSummary(
	    	                request.getApplicationId());
	    	
	    	CitizenResponse citizen =
	    	        citizenFeignClient.getCitizenByUserId(
	    	                summary.getApplication().getCitizenUserId());
	    	
	    	PlanResponse plan =
	    	        planFeignClient.getPlanById(
	    	                summary.getApplication().getPlanId());
	    	
	    	int age = calculateAge(citizen.getDob());

	    	boolean approved = true;

	    	List<String> denialReasons = new ArrayList<>();
	    	
	    	if (plan.getMinAge() != null && age < plan.getMinAge()) {

	    	    approved = false;
	    	    denialReasons.add("Minimum age requirement not met.");
	    	}
	    	
	    	if (plan.getMaxAge() != null && age > plan.getMaxAge()) {

	    	    approved = false;
	    	    denialReasons.add("Maximum age exceeded.");
	    	}
	    	
	    	if (plan.getIncomeLimit() != null
	    	        && summary.getIncome().getAnnualIncome()
	    	                .compareTo(plan.getIncomeLimit()) > 0) {

	    	    approved = false;
	    	    denialReasons.add("Income exceeds plan limit.");
	    	}
	    	
	    	if (Boolean.TRUE.equals(plan.getEmploymentRequired())
	    	        && summary.getIncome().getEmploymentStatus() != EmploymentStatus.EMPLOYED
	    	        && summary.getIncome().getEmploymentStatus() != EmploymentStatus.SELF_EMPLOYED) {

	    	    approved = false;
	    	    denialReasons.add("Employment is required.");
	    	}
	    	
	    	if (plan.getMaxChildren() != null
	    	        && summary.getFamily().getNumberOfChildren() != null
	    	        && summary.getFamily().getNumberOfChildren() > plan.getMaxChildren()) {

	    	    approved = false;
	    	    denialReasons.add("Maximum children limit exceeded.");
	    	}
	    	
	    	EligibilityResult result = EligibilityResult.builder()
	    	        .applicationId(summary.getApplication().getId())
	    	        .citizenId(summary.getApplication().getCitizenId())
	    	        .citizenUserId(summary.getApplication().getCitizenUserId())
	    	        .planId(plan.getId())
	    	        .planName(plan.getPlanName())
	    	        .status(
	    	        	    approved
	    	        	        ? EligibilityStatus.APPROVED
	    	        	        : EligibilityStatus.DENIED
	    	        	)
	    	        .benefitAmount(approved ? plan.getBenefitAmount() : null)
	    	        .denialReason(
	    	                approved
	    	                        ? null
	    	                        : String.join(", ", denialReasons))
	    	        .build();
	    	
	    	EligibilityResult savedResult =
	    	        eligibilityRepository.save(result);
	    	
	    	UpdateApplicationStatusRequest statusRequest =
	    	        new UpdateApplicationStatusRequest();

	    	statusRequest.setStatus(
	    	        approved
	    	                ? ApplicationStatus.APPROVED
	    	                : ApplicationStatus.DENIED);

	    	applicationFeignClient.updateApplicationStatus(
	    	        summary.getApplication().getId(),
	    	        statusRequest);
	    	
	    	return eligibilityMapper.toResponse(savedResult);
    }
    
    private int calculateAge(LocalDate dob) {

        return Period.between(dob, LocalDate.now()).getYears();
    }

    @Override
    public EligibilityResponse getResult(Long applicationId) {

        EligibilityResult result = eligibilityRepository
                .findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Eligibility result not found."));

        return eligibilityMapper.toResponse(result);
    }

}