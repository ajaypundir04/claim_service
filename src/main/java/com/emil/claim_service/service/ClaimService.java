package com.emil.claim_service.service;


import com.emil.claim_service.exception.ResourceNotFoundException;
import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.request.UpdateClaimStatusRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.entity.Claim;
import com.emil.claim_service.entity.User;
import com.emil.claim_service.mapper.ClaimMapper;
import com.emil.claim_service.repository.ClaimRepository;
import com.emil.claim_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final UserRepository userRepository;
    private final ClaimMapper claimMapper;

    public ClaimResponse createClaim(CreateClaimRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatusCode.valueOf(400),
                        "User not found"));


        var claim = claimRepository.save(claimMapper.toEntity(request));
        claim.setUser(user);
        log.info("Creating Claim with number {}", claim.getClaimNumber());
        return claimMapper.toResponse(claim);
    }

    public ClaimResponse updateStatus(String claimNumber, UpdateClaimStatusRequest request) {

        Claim claim = claimRepository.findByClaimNumber(claimNumber).orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(request.getStatus());

        if (request.getRemarks() != null) {
            claim.setRemarks(request.getRemarks());
        }

        claimRepository.save(claim);

        return claimMapper.toResponse(claim);
    }

    public ClaimResponse getClaimByNumber(String claimNumber) {
        return claimRepository.findByClaimNumber(claimNumber)
                .map(claimMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatusCode.valueOf(400),
                        String.format("Claim not found with number:  %s", claimNumber)));
    }

}