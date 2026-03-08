package com.emil.claim_service.service;

import com.emil.claim_service.dto.request.UpdateFunnelRequest;
import com.emil.claim_service.dto.response.FunnelResponse;
import com.emil.claim_service.entity.FunnelConfig;
import com.emil.claim_service.repository.FunnelConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FunnelService {

    private final FunnelConfigRepository repository;

    @Transactional(readOnly = true)
    public FunnelResponse getFunnelBySlug(String slug) {
        FunnelConfig funnel = repository.findById(slug)
                .orElseThrow(() -> new RuntimeException("Funnel not found: " + slug));

        return mapToResponse(funnel);
    }

    @Transactional
    public FunnelResponse updateOrCreateFunnel(String slug, UpdateFunnelRequest request) {
        FunnelConfig funnel = repository.findById(slug)
                .orElse(new FunnelConfig());

        funnel.setSlug(slug);
        funnel.setName(request.getName());
        funnel.setConfig(request.getConfig());

        FunnelConfig saved = repository.save(funnel);
        return mapToResponse(saved);
    }

    private FunnelResponse mapToResponse(FunnelConfig funnel) {
        return FunnelResponse.builder()
                .slug(funnel.getSlug())
                .name(funnel.getName())
                .config(funnel.getConfig())
                .build();
    }
}