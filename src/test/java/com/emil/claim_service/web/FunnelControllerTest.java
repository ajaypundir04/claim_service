package com.emil.claim_service.web;

import com.emil.claim_service.dto.request.UpdateFunnelRequest;
import com.emil.claim_service.dto.response.FunnelResponse;
import com.emil.claim_service.service.FunnelService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FunnelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FunnelService funnelService;

    @InjectMocks
    private FunnelController funnelController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(funnelController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getFunnel_ShouldReturnFunnelResponse() throws Exception {
        String slug = "test-funnel";
        FunnelResponse response = FunnelResponse.builder().build();
        response.setSlug(slug);
        response.setName("Test Funnel");

        when(funnelService.getFunnelBySlug(slug)).thenReturn(response);

        mockMvc.perform(get("/api/funnels/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.name").value("Test Funnel"));
    }

    @Test
    void updateFunnel_ShouldReturnUpdatedFunnelResponse() throws Exception {
        String slug = "test-funnel";
        UpdateFunnelRequest request = new UpdateFunnelRequest();
        request.setName("Updated Funnel");
        
        FunnelResponse response = FunnelResponse.builder().build();
        response.setSlug(slug);
        response.setName("Updated Funnel");

        when(funnelService.updateOrCreateFunnel(eq(slug), any(UpdateFunnelRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/funnels/{slug}", slug)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.name").value("Updated Funnel"));
    }
}
