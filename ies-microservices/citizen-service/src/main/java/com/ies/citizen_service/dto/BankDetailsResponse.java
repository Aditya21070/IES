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
public class BankDetailsResponse {

    private Long id;

    private Long applicationId;

    private String accountHolderName;

    private String accountNumber;

    private String ifscCode;

    private String bankName;

    private String branch;
}
