package com.ies.payment_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.payment_service.dto.PaymentResponse;
import com.ies.payment_service.dto.PaymentSummaryResponse;
import com.ies.payment_service.entity.PaymentTransaction;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(PaymentTransaction payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentReference(payment.getPaymentReference())
                .applicationId(payment.getApplicationId())
                .citizenId(payment.getCitizenId())
                .processingFee(payment.getProcessingFee())
                .paymentStatus(payment.getPaymentStatus())
                .paymentToken(payment.getPaymentToken())
                .tokenExpiry(payment.getTokenExpiry())
                .createdAt(payment.getCreatedAt())
                .build();
    }
    
    public PaymentSummaryResponse toSummary(PaymentTransaction payment) {

        return PaymentSummaryResponse.builder()
                .paymentReference(payment.getPaymentReference())
                .applicationId(payment.getApplicationId())
                .processingFee(payment.getProcessingFee())
                .paymentStatus(payment.getPaymentStatus())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .paidAt(payment.getPaidAt())
                .build();
    }
}