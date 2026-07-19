package com.ies.payment_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.payment_service.enums.PaymentStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;

    private String paymentReference;

    private Long applicationId;

    private Long citizenId;

    private BigDecimal processingFee;

    private PaymentStatus paymentStatus;

    private UUID paymentToken;

    private LocalDateTime tokenExpiry;

    private LocalDateTime createdAt;

}