package com.ies.citizen_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.citizen_service.dto.ApplicationRequest;
import com.ies.citizen_service.dto.ApplicationResponse;
import com.ies.citizen_service.entity.Application;
import com.ies.citizen_service.entity.Citizen;
import com.ies.citizen_service.enums.ApplicationStatus;
import com.ies.citizen_service.exception.BadRequestException;
import com.ies.citizen_service.exception.ResourceNotFoundException;
import com.ies.citizen_service.mapper.ApplicationMapper;
import com.ies.citizen_service.repository.ApplicationRepository;
import com.ies.citizen_service.repository.BankDetailsRepository;
import com.ies.citizen_service.repository.CitizenRepository;
import com.ies.citizen_service.repository.EducationDetailsRepository;
import com.ies.citizen_service.repository.FamilyDetailsRepository;
import com.ies.citizen_service.repository.IncomeDetailsRepository;
import com.ies.citizen_service.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;
	
	private final CitizenRepository citizenRepository;
	
	private final IncomeDetailsRepository incomeDetailsRepository;

	private final EducationDetailsRepository educationDetailsRepository;

	private final FamilyDetailsRepository familyDetailsRepository;

	private final BankDetailsRepository bankDetailsRepository;
	
	private final ApplicationMapper applicationMapper;
	
	private String generateApplicationNumber() {

	    long count = applicationRepository.count() + 1;

	    return String.format("APP%06d", count);
	}
	
	@Override
	public ApplicationResponse createApplication(ApplicationRequest request) {

	    Citizen citizen = citizenRepository.findById(request.getCitizenId())
	            .orElseThrow(() -> new ResourceNotFoundException("Citizen not found"));

	    Application application = Application.builder()
	            .applicationNumber(generateApplicationNumber())
	            .citizen(citizen)
	            .planId(request.getPlanId())
	            .status(ApplicationStatus.DRAFT)
	            .build();

	    Application savedApplication = applicationRepository.save(application);

	    return applicationMapper.toResponse(savedApplication);
	}

	@Override
	public List<ApplicationResponse> getApplicationsByCitizen(Long citizenId) {

	    return applicationRepository.findByCitizenId(citizenId)
	            .stream()
	            .map(applicationMapper::toResponse)
	            .toList();
	}

	@Override
	public ApplicationResponse getApplication(Long id) {

	    Application application = applicationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

	    return applicationMapper.toResponse(application);
	}
	
	@Override
	public ApplicationResponse submitApplication(Long applicationId) {

	    Application application = applicationRepository.findById(applicationId)
	            .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

	    if (application.getStatus() != ApplicationStatus.DRAFT) {
	        throw new BadRequestException("Application has already been submitted");
	    }

	    if (incomeDetailsRepository.findByApplicationId(applicationId).isEmpty()) {
	        throw new BadRequestException("Income Details are missing");
	    }

	    if (educationDetailsRepository.findByApplicationId(applicationId).isEmpty()) {
	        throw new BadRequestException("Education Details are missing");
	    }

	    if (familyDetailsRepository.findByApplicationId(applicationId).isEmpty()) {
	        throw new BadRequestException("Family Details are missing");
	    }

	    if (bankDetailsRepository.findByApplicationId(applicationId).isEmpty()) {
	        throw new BadRequestException("Bank Details are missing");
	    }

	    application.setStatus(ApplicationStatus.SUBMITTED);
	    application.setSubmittedAt(LocalDateTime.now());

	    Application saved = applicationRepository.save(application);

	    return applicationMapper.toResponse(saved);
	}

}
