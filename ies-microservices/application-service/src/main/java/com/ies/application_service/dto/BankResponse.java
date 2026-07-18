package com.ies.application_service.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {

    private Long id;

    private Long applicationId;

    private String bankName;

    private String accountHolderName;

    private String accountNumber;

    private String ifscCode;

    private String branchName;

    private LocalDateTime createdAt;
}