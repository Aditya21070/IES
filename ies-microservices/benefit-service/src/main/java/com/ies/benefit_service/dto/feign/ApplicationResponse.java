package com.ies.benefit_service.dto.feign;

import com.ies.benefit_service.enums.ApplicationStatus;

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
public class ApplicationResponse {

    private Long id;

    private Long citizenId;

    private Long planId;

    private ApplicationStatus status;

}