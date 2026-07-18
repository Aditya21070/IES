package com.ies.eligibility_service.dto.feign;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ies.eligibility_service.enums.Gender;

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
public class KidResponse {

    private Long id;

    private Long applicationId;

    private String childName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String aadhaarNumber;

    private LocalDateTime createdAt;
}