package com.ies.citizen_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @NotNull(message = "Citizen Id is required")
    private Long citizenId;

    @NotNull(message = "Plan Id is required")
    private Long planId;
}