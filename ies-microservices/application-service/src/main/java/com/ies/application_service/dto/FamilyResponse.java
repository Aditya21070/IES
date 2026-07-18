package com.ies.application_service.dto;

import java.time.LocalDateTime;

import com.ies.application_service.enums.MaritalStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyResponse {

    private Long id;

    private Long applicationId;

    private MaritalStatus maritalStatus;

    private String spouseName;

    private Integer spouseAge;

    private Integer numberOfChildren;

    private LocalDateTime createdAt;

}