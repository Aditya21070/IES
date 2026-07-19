package com.ies.application_service.dto.dashboard;

import java.time.LocalDateTime;

import com.ies.application_service.enums.ApplicationStatus;

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
public class RecentApplicationsResponse {

    private Long applicationId;

    private String applicationNumber;

    private Long citizenId;

    private Long planId;

    private ApplicationStatus status;

    private LocalDateTime createdAt;

}