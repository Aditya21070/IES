package com.ies.eligibility_service.dto.feign;

import java.time.LocalDateTime;

import com.ies.eligibility_service.enums.MaritalStatus;

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
public class FamilyResponse {

    private Long id;

    private Long applicationId;

    private MaritalStatus maritalStatus;

    private String spouseName;

    private Integer spouseAge;

    private Integer numberOfChildren;

    private LocalDateTime createdAt;

}