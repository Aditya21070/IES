package com.ies.application_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ies.application_service.enums.EmploymentStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "income_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long applicationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentStatus employmentStatus;

    @Column(nullable = false)
    private BigDecimal annualIncome;

    @Column(nullable = false)
    private BigDecimal salaryIncome;

    @Column(nullable = false)
    private BigDecimal propertyIncome;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}