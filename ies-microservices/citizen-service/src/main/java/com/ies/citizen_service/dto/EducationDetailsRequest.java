package com.ies.citizen_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDetailsRequest {

    @NotNull
    private Long applicationId;

    @NotBlank
    private String highestQualification;

    @NotNull
    private Integer graduationYear;

    @NotBlank
    private String university;
}
