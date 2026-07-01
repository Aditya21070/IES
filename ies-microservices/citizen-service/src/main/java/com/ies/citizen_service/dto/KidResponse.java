package com.ies.citizen_service.dto;

import java.time.LocalDate;

import com.ies.citizen_service.enums.Gender;

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

    private String kidName;

    private LocalDate dob;

    private Gender gender;

}
