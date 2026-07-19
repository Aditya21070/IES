package com.ies.payment_service.dto.feign;

import java.util.UUID;

import com.ies.payment_service.enums.ApplicationStatus;

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

}
