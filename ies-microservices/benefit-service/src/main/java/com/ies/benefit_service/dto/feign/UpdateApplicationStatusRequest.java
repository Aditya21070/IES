package com.ies.benefit_service.dto.feign;

import com.ies.benefit_service.enums.ApplicationStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateApplicationStatusRequest {

    private ApplicationStatus status;

}