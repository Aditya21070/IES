package com.ies.application_service.entity;

import java.time.LocalDateTime;

import com.ies.application_service.enums.EducationQualification;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long applicationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EducationQualification highestQualification;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String institutionName;

    @Column(nullable = false)
    private Integer graduationYear;

    @Column(nullable = false)
    private Double percentage;

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