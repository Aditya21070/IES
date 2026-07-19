package com.ies.payment_service.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ies.payment_service.entity.PaymentTransaction;
import com.ies.payment_service.enums.PaymentStatus;

public interface PaymentRepository
        extends JpaRepository<PaymentTransaction, Long> {

    Optional<PaymentTransaction> findByPaymentReference(String paymentReference);

    Optional<PaymentTransaction> findByPaymentToken(UUID paymentToken);

    Optional<PaymentTransaction> findByApplicationId(Long applicationId);

    Optional<PaymentTransaction> findByTransactionId(String transactionId);

    List<PaymentTransaction> findByCitizenUserId(UUID citizenUserId);

    List<PaymentTransaction> findByPaymentStatus(PaymentStatus paymentStatus);

    boolean existsByApplicationId(Long applicationId);

    long countByPaymentStatus(PaymentStatus paymentStatus);
}