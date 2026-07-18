package com.ies.application_service.entity;

import java.time.LocalDateTime;

import com.ies.application_service.enums.MaritalStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "family_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long applicationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaritalStatus maritalStatus;

    private String spouseName;

    private Integer spouseAge;

    @Column(nullable = false)
    private Integer numberOfChildren;

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