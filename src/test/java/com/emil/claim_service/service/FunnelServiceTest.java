package com.emil.claim_service.service;

import com.emil.claim_service.dto.request.UpdateFunnelRequest;
import com.emil.claim_service.dto.response.FunnelResponse;
import com.emil.claim_service.entity.FunnelConfig;
import com.emil.claim_service.repository.FunnelConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FunnelServiceTest {

    @Mock
    private FunnelConfigRepository repository;

    @InjectMocks
    private FunnelService funnelService;

    @Test
    void getFunnelBySlug_ShouldReturnFunnelResponse() {
        String slug = "test-funnel";
        FunnelConfig funnel = new FunnelConfig();
        funnel.setSlug(slug);
        funnel.setName("Test Funnel");

        when(repository.findById(slug)).thenReturn(Optional.of(funnel));

        FunnelResponse result = funnelService.getFunnelBySlug(slug);

        assertEquals(slug, result.getSlug());
        assertEquals("Test Funnel", result.getName());
    }

    @Test
    void getFunnelBySlug_ShouldThrowRuntimeException_WhenFunnelNotFound() {
        String slug = "test-funnel";

        when(repository.findById(slug)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> funnelService.getFunnelBySlug(slug));
    }

    @Test
    void updateOrCreateFunnel_ShouldReturnUpdatedFunnelResponse() {
        String slug = "test-funnel";
        UpdateFunnelRequest request = new UpdateFunnelRequest();
        request.setName("Updated Funnel");

        FunnelConfig funnel = new FunnelConfig();
        funnel.setSlug(slug);
        funnel.setName("Updated Funnel");

        when(repository.findById(slug)).thenReturn(Optional.of(funnel));
        when(repository.save(any(FunnelConfig.class))).thenReturn(funnel);

        FunnelResponse result = funnelService.updateOrCreateFunnel(slug, request);

        assertEquals(slug, result.getSlug());
        assertEquals("Updated Funnel", result.getName());
    }
}
