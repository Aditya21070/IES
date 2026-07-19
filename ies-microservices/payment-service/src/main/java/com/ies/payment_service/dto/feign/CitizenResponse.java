package com.ies.payment_service.dto.feign;

import java.util.UUID;

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
public class CitizenResponse {

    private Long id;

    private UUID userId;

    private String fullName;

    private String email;

}
