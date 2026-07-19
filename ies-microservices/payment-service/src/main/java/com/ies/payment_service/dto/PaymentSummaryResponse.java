package com.ies.payment_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.payment_service.enums.PaymentMethod;
import com.ies.payment_service.enums.PaymentStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSummaryResponse {

    private String paymentReference;

    private Long applicationId;

    private BigDecimal processingFee;

    private PaymentStatus paymentStatus;

    private PaymentMethod paymentMethod;

    private String transactionId;

    private LocalDateTime paidAt;

}