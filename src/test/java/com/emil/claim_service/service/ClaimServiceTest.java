package com.emil.claim_service.service;

import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.request.UpdateClaimStatusRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.entity.Claim;
import com.emil.claim_service.entity.ClaimStatus;
import com.emil.claim_service.entity.User;
import com.emil.claim_service.exception.ResourceNotFoundException;
import com.emil.claim_service.mapper.ClaimMapper;
import com.emil.claim_service.repository.ClaimRepository;
import com.emil.claim_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClaimMapper claimMapper;

    @InjectMocks
    private ClaimService claimService;

    @Test
    void createClaim_ShouldReturnClaimResponse() {
        CreateClaimRequest request = new CreateClaimRequest();
        request.setUserId(UUID.randomUUID());

        User user = new User();
        user.setId(request.getUserId());

        Claim claim = new Claim();
        claim.setClaimNumber("CLM-123");

        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber("CLM-123");

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));
        when(claimMapper.toEntity(request)).thenReturn(claim);
        when(claimRepository.save(any(Claim.class))).thenReturn(claim);
        when(claimMapper.toResponse(claim)).thenReturn(response);

        ClaimResponse result = claimService.createClaim(request);

        assertEquals("CLM-123", result.getClaimNumber());
    }

    @Test
    void createClaim_ShouldThrowResourceNotFoundException_WhenUserNotFound() {
        CreateClaimRequest request = new CreateClaimRequest();
        request.setUserId(UUID.randomUUID());

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> claimService.createClaim(request));
    }

    @Test
    void updateStatus_ShouldReturnUpdatedClaimResponse() {
        String claimNumber = "CLM-123";
        UpdateClaimStatusRequest request = new UpdateClaimStatusRequest();
        request.setStatus(ClaimStatus.CLOSED);

        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);

        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber(claimNumber);
        response.setStatus("CLOSED");

        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(Optional.of(claim));
        when(claimRepository.save(any(Claim.class))).thenReturn(claim);
        when(claimMapper.toResponse(claim)).thenReturn(response);

        ClaimResponse result = claimService.updateStatus(claimNumber, request);

        assertEquals("CLOSED", result.getStatus());
    }

    @Test
    void updateStatus_ShouldThrowRuntimeException_WhenClaimNotFound() {
        String claimNumber = "CLM-123";
        UpdateClaimStatusRequest request = new UpdateClaimStatusRequest();
        request.setStatus(ClaimStatus.CLOSED);

        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> claimService.updateStatus(claimNumber, request));
    }

    @Test
    void getClaimByNumber_ShouldReturnClaimResponse() {
        String claimNumber = "CLM-123";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);

        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber(claimNumber);

        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(Optional.of(claim));
        when(claimMapper.toResponse(claim)).thenReturn(response);

        ClaimResponse result = claimService.getClaimByNumber(claimNumber);

        assertEquals("CLM-123", result.getClaimNumber());
    }

    @Test
    void getClaimByNumber_ShouldThrowResourceNotFoundException_WhenClaimNotFound() {
        String claimNumber = "CLM-123";

        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> claimService.getClaimByNumber(claimNumber));
    }
}
