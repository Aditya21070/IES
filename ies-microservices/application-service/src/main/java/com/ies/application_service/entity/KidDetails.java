package com.ies.application_service.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ies.application_service.enums.Gender;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kid_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KidDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long applicationId;

    @Column(nullable = false)
    private String childName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String aadhaarNumber;

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