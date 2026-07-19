package com.ies.payment_service.service;

import java.util.UUID;

import com.ies.payment_service.dto.InitiatePaymentRequest;
import com.ies.payment_service.dto.PaymentResponse;
import com.ies.payment_service.dto.PaymentSummaryResponse;
import com.ies.payment_service.dto.VerifyPaymentRequest;

public interface PaymentService {

    PaymentResponse initiatePayment(
            InitiatePaymentRequest request);
    
    PaymentSummaryResponse getPaymentByToken(UUID paymentToken);
    
    PaymentSummaryResponse completePayment(
            UUID paymentToken,
            VerifyPaymentRequest request);

}