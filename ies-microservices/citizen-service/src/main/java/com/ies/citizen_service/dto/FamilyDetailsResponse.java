package com.ies.citizen_service.dto;

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
public class FamilyDetailsResponse {

    private Long id;

    private Long applicationId;

    private String maritalStatus;

    private String spouseName;

    private Integer familyMembers;

}
