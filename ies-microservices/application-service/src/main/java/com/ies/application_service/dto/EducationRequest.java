package com.ies.application_service.dto;

import com.ies.application_service.enums.EducationQualification;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

    @NotNull(message = "Qualification is required")
    private EducationQualification highestQualification;

    @NotBlank(message = "Course Name is required")
    private String courseName;

    @NotBlank(message = "Institution Name is required")
    private String institutionName;

    @NotNull(message = "Graduation Year is required")
    @Min(value = 1950)
    @Max(value = 2100)
    private Integer graduationYear;

    @NotNull(message = "Percentage is required")
    @Min(value = 0)
    @Max(value = 100)
    private Double percentage;
}