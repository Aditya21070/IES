package com.ies.citizen_service.entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private String maritalStatus;

    private String spouseName;

    @Column(nullable = false)
    private Integer familyMembers;
}
