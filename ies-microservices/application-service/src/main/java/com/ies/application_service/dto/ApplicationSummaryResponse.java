package com.ies.application_service.dto;

import java.util.List;

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
public class ApplicationSummaryResponse {

    private ApplicationResponse application;

    private IncomeResponse income;

    private EducationResponse education;

    private FamilyResponse family;

    private List<KidResponse> kids;

    private BankResponse bank;
}