package com.emil.claim_service.web;

import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.request.UpdateClaimStatusRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
@Validated
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ClaimResponse createClaim(
            @RequestBody CreateClaimRequest request
    ) {
        return claimService.createClaim(request);
    }

    @PatchMapping("/{claimNumber}/status")
    public ClaimResponse updateStatus(
            @PathVariable String claimNumber,
            @Valid @RequestBody UpdateClaimStatusRequest request
    ) {

        return claimService.updateStatus(claimNumber, request);
    }
}