package com.ies.application_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ies.application_service.dto.ApplicationRequest;
import com.ies.application_service.dto.ApplicationResponse;
import com.ies.application_service.dto.ApplicationSummaryResponse;
import com.ies.application_service.dto.feign.CitizenResponse;
import com.ies.application_service.entity.Application;
import com.ies.application_service.entity.BankDetails;
import com.ies.application_service.entity.EducationDetails;
import com.ies.application_service.entity.FamilyDetails;
import com.ies.application_service.entity.IncomeDetails;
import com.ies.application_service.entity.KidDetails;
import com.ies.application_service.enums.ApplicationStatus;
import com.ies.application_service.exceptions.BadRequestException;
import com.ies.application_service.exceptions.ResourceNotFoundException;
import com.ies.application_service.feign.CitizenFeignClient;
import com.ies.application_service.mapper.ApplicationMapper;
import com.ies.application_service.mapper.BankMapper;
import com.ies.application_service.mapper.EducationMapper;
import com.ies.application_service.mapper.FamilyMapper;
import com.ies.application_service.mapper.IncomeMapper;
import com.ies.application_service.mapper.KidMapper;
import com.ies.application_service.repository.ApplicationRepository;
import com.ies.application_service.repository.BankDetailsRepository;
import com.ies.application_service.repository.EducationDetailsRepository;
import com.ies.application_service.repository.FamilyDetailsRepository;
import com.ies.application_service.repository.IncomeDetailsRepository;
import com.ies.application_service.repository.KidDetailsRepository;
import com.ies.application_service.security.JwtService;
import com.ies.application_service.service.ApplicationService;
import com.ies.application_service.service.security.ApplicationSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
	
	private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    private final JwtService jwtService;

    private final CitizenFeignClient citizenFeignClient;
    
    private final IncomeDetailsRepository incomeRepository;

    private final EducationDetailsRepository educationRepository;

    private final FamilyDetailsRepository familyRepository;

    private final KidDetailsRepository kidRepository;

    private final BankDetailsRepository bankRepository;

    private final ApplicationSecurityService applicationSecurityService;
    
    private final IncomeMapper incomeDetailsMapper;

    private final EducationMapper educationDetailsMapper;

    private final FamilyMapper familyDetailsMapper;

    private final KidMapper kidDetailsMapper;

    private final BankMapper bankDetailsMapper;
    
    @Override
    public ApplicationResponse createApplication(
            ApplicationRequest request,
            String token) {

        token = token.substring(7);

        UUID userId = jwtService.extractUserId(token);

        CitizenResponse citizen =
                citizenFeignClient.getCitizenByUserId(userId);

        if (!Boolean.TRUE.equals(citizen.getPortalAccess())) {
            throw new BadRequestException(
                    "Portal access is disabled for this citizen.");
        }

        Application application = Application.builder()
                .applicationNumber(generateApplicationNumber())
                .citizenId(citizen.getId())
                .citizenUserId(citizen.getUserId())
                .planId(request.getPlanId())
                .status(ApplicationStatus.DRAFT)
                .build();

        Application saved =
                applicationRepository.save(application);

        return applicationMapper.toResponse(saved);
    }
    
    private String generateApplicationNumber() {
        return "APP-" + System.currentTimeMillis();
    }
    
    @Override
    public ApplicationResponse submitApplication(
            Long applicationId,
            String token) {

        // 1. Verify ownership
        applicationSecurityService.validateOwnership(
                applicationId,
                token);

        // 2. Fetch application
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found."));

        // 3. Only DRAFT applications can be submitted
        if (application.getStatus() != ApplicationStatus.DRAFT) {
            throw new BadRequestException(
                    "Application has already been submitted.");
        }

        // 4. Income
        if (!incomeRepository.existsByApplicationId(applicationId)) {
            throw new BadRequestException(
                    "Income details are missing.");
        }

        // 5. Education
        if (!educationRepository.existsByApplicationId(applicationId)) {
            throw new BadRequestException(
                    "Education details are missing.");
        }

        // 6. Family
        FamilyDetails family = familyRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "Family details are missing."));

        // 7. Kids validation
        long kidCount = kidRepository.countByApplicationId(applicationId);

        if (family.getNumberOfChildren() != null &&
                kidCount != family.getNumberOfChildren()) {

            throw new BadRequestException(
                    "Please complete children details.");
        }

        // 8. Bank
        if (!bankRepository.existsByApplicationId(applicationId)) {
            throw new BadRequestException(
                    "Bank details are missing.");
        }

        // 9. Submit
        application.setStatus(ApplicationStatus.SUBMITTED);

        Application savedApplication =
                applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }
    
    @Override
    public ApplicationResponse getApplication(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found."));

        return applicationMapper.toResponse(application);
    }
    
    @Override
    public ApplicationSummaryResponse getApplicationSummary(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found."));

        IncomeDetails income = incomeRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income details not found."));

        EducationDetails education = educationRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Education details not found."));

        FamilyDetails family = familyRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Family details not found."));

        BankDetails bank = bankRepository.findByApplicationId(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank details not found."));

        List<KidDetails> kids = kidRepository.findAllByApplicationId(applicationId);

        return ApplicationSummaryResponse.builder()
                .application(applicationMapper.toResponse(application))
                .income(incomeDetailsMapper.toResponse(income))
                .education(educationDetailsMapper.toResponse(education))
                .family(familyDetailsMapper.toResponse(family))
                .kids(
                        kids.stream()
                                .map(kidDetailsMapper::toResponse)
                                .toList()
                )
                .bank(bankDetailsMapper.toResponse(bank))
                .build();
    }
    
    @Override
    public ApplicationResponse updateStatus(
            Long applicationId,
            ApplicationStatus status) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        application.setStatus(status);

        Application saved = applicationRepository.save(application);

        return applicationMapper.toResponse(saved);
    }
}