package com.ies.citizen_service.dto;

import java.time.LocalDate;

import com.ies.citizen_service.enums.Gender;

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
public class KidRequest {

    @NotNull
    private Long applicationId;

    @NotBlank
    private String kidName;

    @NotNull
    private LocalDate dob;

    @NotNull
    private Gender gender;

}
