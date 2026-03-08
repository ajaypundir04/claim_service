package com.emil.claim_service.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateClaimRequest {

    private UUID userId;
    private String title;
    private String description;
}