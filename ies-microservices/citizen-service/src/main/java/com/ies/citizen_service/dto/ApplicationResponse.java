package com.ies.citizen_service.dto;

import java.time.LocalDateTime;

import com.ies.citizen_service.enums.ApplicationStatus;

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

    private String applicationNumber;

    private Long citizenId;

    private Long planId;

    private ApplicationStatus status;

    private LocalDateTime createdAt;
}
