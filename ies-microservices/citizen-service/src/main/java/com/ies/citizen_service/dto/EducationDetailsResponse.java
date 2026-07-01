package com.ies.citizen_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationDetailsResponse {

    private Long id;

    private Long applicationId;

    private String highestQualification;

    private Integer graduationYear;

    private String university;
}
