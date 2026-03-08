package com.emil.claim_service.mapper;

import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.entity.Claim;
import com.emil.claim_service.entity.ClaimStatus;
import com.emil.claim_service.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClaimMapperTest {

    private final ClaimMapper claimMapper = Mappers.getMapper(ClaimMapper.class);

    @Test
    void toResponse_ShouldMapClaimToClaimResponse() {
        User user = new User();
        user.setUsername("testuser");

        Claim claim = new Claim();
        claim.setClaimNumber("CLM-123");
        claim.setUser(user);
        claim.setStatus(ClaimStatus.OPEN);
        claim.setCreatedAt(LocalDateTime.now());

        ClaimResponse response = claimMapper.toResponse(claim);

        assertEquals("CLM-123", response.getClaimNumber());
        assertEquals("testuser", response.getUsername());
        assertEquals("OPEN", response.getStatus());
    }

    @Test
    void toEntity_ShouldMapCreateClaimRequestToClaim() {
        CreateClaimRequest request = new CreateClaimRequest();
        request.setTitle("Test Claim");
        request.setDescription("Test Description");

        Claim claim = claimMapper.toEntity(request);

        assertEquals("Test Claim", claim.getTitle());
        assertEquals("Test Description", claim.getDescription());
        assertEquals("OPEN", claim.getStatus().name());
        assertNotNull(claim.getCreatedAt());
    }
}
