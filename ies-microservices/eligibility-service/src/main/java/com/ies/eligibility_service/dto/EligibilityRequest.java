package com.ies.eligibility_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRequest {

    @NotNull(message = "Application ID is required")
    private Long applicationId;
}