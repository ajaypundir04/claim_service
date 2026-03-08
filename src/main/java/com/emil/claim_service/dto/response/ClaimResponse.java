package com.emil.claim_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClaimResponse {

    private String claimNumber;

    private String title;

    private String description;

    private String remarks;

    private String status;

    private LocalDateTime createdAt;

    private String username;
}