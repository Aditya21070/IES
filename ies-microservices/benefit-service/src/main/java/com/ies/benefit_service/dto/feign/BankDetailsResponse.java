package com.ies.benefit_service.dto.feign;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetailsResponse {

    private Long id;

    private Long applicationId;

    private String bankName;

    private String accountHolderName;

    private String accountNumber;

    private String ifscCode;

    private String branchName;

}