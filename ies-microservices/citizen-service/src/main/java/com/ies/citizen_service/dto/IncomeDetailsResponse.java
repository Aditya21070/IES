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
public class IncomeDetailsResponse {

    private Long id;

    private Long applicationId;

    private Double annualIncome;

    private String employmentStatus;

    private String occupation;

    private String employerName;
}
