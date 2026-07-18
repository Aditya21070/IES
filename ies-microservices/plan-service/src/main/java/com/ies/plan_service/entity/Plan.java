package com.ies.plan_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Information
    @Column(nullable = false, unique = true)
    private String planName;

    @Column(nullable = false)
    private String planCategory;

    @Column(length = 1000)
    private String description;

    // Benefit
    @Column(nullable = false)
    private BigDecimal benefitAmount;

    // Eligibility Criteria
    private Integer minAge;

    private Integer maxAge;

    private BigDecimal incomeLimit;

    private Boolean employmentRequired;

    private Integer maxChildren;

    // Status
    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean deleted;

    // Audit
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (active == null) {
            active = true;
        }

        if (deleted == null) {
            deleted = false;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}