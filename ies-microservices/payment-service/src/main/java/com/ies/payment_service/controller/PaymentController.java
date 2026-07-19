package com.ies.payment_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.payment_service.dto.InitiatePaymentRequest;
import com.ies.payment_service.dto.PaymentResponse;
import com.ies.payment_service.dto.PaymentSummaryResponse;
import com.ies.payment_service.dto.VerifyPaymentRequest;
import com.ies.payment_service.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(
            @Valid @RequestBody InitiatePaymentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.initiatePayment(request));
    }
    
    @GetMapping("/{paymentToken}")
    public ResponseEntity<PaymentSummaryResponse> getPaymentByToken(
            @PathVariable UUID paymentToken) {

        return ResponseEntity.ok(
                paymentService.getPaymentByToken(paymentToken));
    }
    
    @PostMapping("/{paymentToken}/pay")
    public ResponseEntity<PaymentSummaryResponse> completePayment(
            @PathVariable UUID paymentToken,
            @Valid @RequestBody VerifyPaymentRequest request) {

        return ResponseEntity.ok(
                paymentService.completePayment(
                        paymentToken,
                        request));
    }
}