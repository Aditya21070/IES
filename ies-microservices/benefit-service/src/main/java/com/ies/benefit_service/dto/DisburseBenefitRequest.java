package com.ies.benefit_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisburseBenefitRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

}