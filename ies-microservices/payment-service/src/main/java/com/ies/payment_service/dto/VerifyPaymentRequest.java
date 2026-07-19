package com.ies.payment_service.dto;

import com.ies.payment_service.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyPaymentRequest {

    @NotNull
    private PaymentMethod paymentMethod;

}