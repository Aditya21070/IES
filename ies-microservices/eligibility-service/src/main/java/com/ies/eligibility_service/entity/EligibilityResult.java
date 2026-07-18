package com.ies.eligibility_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.eligibility_service.enums.EligibilityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eligibility_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long applicationId;

    @Column(nullable = false)
    private Long citizenId;

    @Column(nullable = false)
    private UUID citizenUserId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private String planName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EligibilityStatus status;

    private BigDecimal benefitAmount;

    private String denialReason;

    private LocalDateTime processedAt;

    @PrePersist
    public void onCreate() {
        processedAt = LocalDateTime.now();
    }
}