package com.ies.payment_service.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ies.payment_service.dto.InitiatePaymentRequest;
import com.ies.payment_service.dto.PaymentResponse;
import com.ies.payment_service.dto.PaymentSummaryResponse;
import com.ies.payment_service.dto.VerifyPaymentRequest;
import com.ies.payment_service.dto.feign.ApplicationResponse;
import com.ies.payment_service.dto.feign.CitizenResponse;
import com.ies.payment_service.dto.feign.UpdateApplicationStatusRequest;
import com.ies.payment_service.entity.PaymentTransaction;
import com.ies.payment_service.enums.ApplicationStatus;
import com.ies.payment_service.enums.PaymentStatus;
import com.ies.payment_service.feign.ApplicationFeignClient;
import com.ies.payment_service.feign.CitizenFeignClient;
import com.ies.payment_service.mapper.PaymentMapper;
import com.ies.payment_service.repository.PaymentRepository;
import com.ies.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ApplicationFeignClient applicationFeignClient;
    private final CitizenFeignClient citizenFeignClient;

    @Override
    public PaymentResponse initiatePayment(InitiatePaymentRequest request) {

        // Check if payment already exists
        if (paymentRepository.existsByApplicationId(request.getApplicationId())) {
            throw new RuntimeException(
                    "Payment has already been initiated for this application.");
        }

        // Fetch application
        ApplicationResponse application =
                applicationFeignClient.getApplication(
                        request.getApplicationId());

        // Payment can only be initiated after approval
        if (application.getStatus() != ApplicationStatus.APPROVED) {
            throw new RuntimeException(
                    "Payment can only be initiated for approved applications.");
        }

        // Fetch citizen
        CitizenResponse citizen =
                citizenFeignClient.getCitizen(
                        application.getCitizenId());

        // Generate payment reference
        String paymentReference =
                "PAY-" + System.currentTimeMillis();

        // Generate secure payment token
        UUID paymentToken = UUID.randomUUID();

        // Token expires after 30 minutes
        LocalDateTime tokenExpiry =
                LocalDateTime.now().plusMinutes(30);

        // Processing fee (temporary)
        BigDecimal processingFee =
                BigDecimal.valueOf(100);

        // Build payment entity
        PaymentTransaction payment =
                PaymentTransaction.builder()
                        .paymentReference(paymentReference)
                        .applicationId(application.getId())
                        .citizenId(citizen.getId())
                        .citizenUserId(citizen.getUserId())
                        .processingFee(processingFee)
                        .paymentStatus(PaymentStatus.PENDING)
                        .paymentToken(paymentToken)
                        .tokenExpiry(tokenExpiry)
                        .build();

        // Save payment
        payment = paymentRepository.save(payment);

        // Return response
        return paymentMapper.toResponse(payment);
    }
    
    @Override
    public PaymentSummaryResponse getPaymentByToken(UUID paymentToken) {

        PaymentTransaction payment =
                paymentRepository.findByPaymentToken(paymentToken)
                        .orElseThrow(() ->
                                new RuntimeException("Invalid payment token"));

        if (payment.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Payment link has expired.");
        }

        return paymentMapper.toSummary(payment);
    }
    
    @Override
    public PaymentSummaryResponse completePayment(
            UUID paymentToken,
            VerifyPaymentRequest request) {

	    	PaymentTransaction payment =
	    	        paymentRepository.findByPaymentToken(paymentToken)
	    	                .orElseThrow(() ->
	    	                        new RuntimeException("Invalid payment token"));
    	
	    	if (payment.getTokenExpiry().isBefore(LocalDateTime.now())) {

	    	    payment.setPaymentStatus(PaymentStatus.EXPIRED);

	    	    paymentRepository.save(payment);

	    	    throw new RuntimeException("Payment link expired.");
	    	}
	    	
	    	if (payment.getPaymentStatus() == PaymentStatus.SUCCESS) {

	    	    throw new RuntimeException("Payment already completed.");
	    	}
	    	
	    	payment.setPaymentMethod(request.getPaymentMethod());

	    	payment.setPaymentStatus(PaymentStatus.SUCCESS);

	    	payment.setPaidAt(LocalDateTime.now());

	    	payment.setTransactionId(
	    	        "TXN-" + System.currentTimeMillis());
	    	
	    	paymentRepository.save(payment);
	    	
	    	applicationFeignClient.updateApplicationStatus(

	    	        payment.getApplicationId(),

	    	        UpdateApplicationStatusRequest.builder()
	    	                .status(ApplicationStatus.PAYMENT_COMPLETED)
	    	                .build()

	    	);
        
	    	return paymentMapper.toSummary(payment);
    }
}