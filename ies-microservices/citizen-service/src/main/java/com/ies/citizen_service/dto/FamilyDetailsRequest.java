package com.ies.citizen_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDetailsRequest {

    @NotNull
    private Long applicationId;

    @NotBlank
    private String maritalStatus;

    private String spouseName;

    @NotNull
    private Integer familyMembers;

}
