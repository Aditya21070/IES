package com.ies.application_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.application_service.enums.ApplicationStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

    private Long id;

    private String applicationNumber;

    private Long citizenId;

    private UUID citizenUserId;

    private Long planId;

    private ApplicationStatus status;

    private LocalDateTime createdAt;

}