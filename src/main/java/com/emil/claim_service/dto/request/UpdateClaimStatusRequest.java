package com.emil.claim_service.dto.request;

import com.emil.claim_service.entity.ClaimStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateClaimStatusRequest {

    @NotNull(message = "Status is required")
    private ClaimStatus status;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;
}