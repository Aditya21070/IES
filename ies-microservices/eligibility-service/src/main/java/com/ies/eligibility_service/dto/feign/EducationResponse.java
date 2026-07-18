package com.ies.eligibility_service.dto.feign;

import java.time.LocalDateTime;

import com.ies.eligibility_service.enums.EducationQualification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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