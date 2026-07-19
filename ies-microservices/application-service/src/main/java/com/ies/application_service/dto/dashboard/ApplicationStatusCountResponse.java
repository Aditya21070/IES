package com.ies.application_service.dto.dashboard;

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
public class ApplicationStatusCountResponse {

    private ApplicationStatus status;

    private long count;

}