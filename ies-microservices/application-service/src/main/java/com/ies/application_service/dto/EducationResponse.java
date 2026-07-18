package com.ies.application_service.dto;

import java.time.LocalDateTime;

import com.ies.application_service.enums.EducationQualification;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationResponse {

    private Long id;

    private Long applicationId;

    private EducationQualification highestQualification;

    private String courseName;

    private String institutionName;

    private Integer graduationYear;

    private Double percentage;

    private LocalDateTime createdAt;
}