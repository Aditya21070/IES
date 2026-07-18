package com.ies.eligibility_service.dto.feign;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ies.eligibility_service.enums.ApplicationStatus;

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

    private UUID citizenUserId;

    private Long planId;

    private ApplicationStatus status;

    private LocalDateTime createdAt;

}