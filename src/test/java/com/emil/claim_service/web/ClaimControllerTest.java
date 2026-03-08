package com.emil.claim_service.web;

import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.request.UpdateClaimStatusRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.entity.ClaimStatus;
import com.emil.claim_service.service.ClaimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ClaimControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClaimService claimService;

    @InjectMocks
    private ClaimController claimController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createClaim_ShouldReturnClaimResponse() throws Exception {
        CreateClaimRequest request = new CreateClaimRequest();
        // Set properties for request if needed
        
        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber("CLM-123");
        
        when(claimService.createClaim(any(CreateClaimRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/claims")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.claimNumber").value("CLM-123"));
    }

    @Test
    void updateStatus_ShouldReturnUpdatedClaimResponse() throws Exception {
        String claimNumber = "CLM-123";
        UpdateClaimStatusRequest request = new UpdateClaimStatusRequest();
        request.setStatus(ClaimStatus.CLOSED);
        
        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber(claimNumber);
        response.setStatus("CLOSED");

        when(claimService.updateStatus(eq(claimNumber), any(UpdateClaimStatusRequest.class))).thenReturn(response);

        mockMvc.perform(patch("/api/claims/{claimNumber}/status", claimNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.claimNumber").value(claimNumber))
                .andExpect(jsonPath("$.status").value("CLOSED"));
    }

    @Test
    void getClaim_ShouldReturnClaimResponse() throws Exception {
        String claimNumber = "CLM-123";
        ClaimResponse response = new ClaimResponse();
        response.setClaimNumber(claimNumber);

        when(claimService.getClaimByNumber(claimNumber)).thenReturn(response);

        mockMvc.perform(get("/api/claims/{claimNumber}", claimNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.claimNumber").value(claimNumber));
    }
}
