package com.ies.benefit_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.benefit_service.enums.BenefitStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "benefit_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long applicationId;

    @Column(nullable = false)
    private Long citizenId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private BigDecimal benefitAmount;

    @Column(nullable = false, unique = true)
    private String transactionReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BenefitStatus benefitStatus;

    private LocalDateTime sentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (benefitStatus == null) {
            benefitStatus = BenefitStatus.PENDING;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}