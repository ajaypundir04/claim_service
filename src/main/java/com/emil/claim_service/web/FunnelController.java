package com.emil.claim_service.web;

import com.emil.claim_service.dto.request.UpdateFunnelRequest;
import com.emil.claim_service.dto.response.FunnelResponse;
import com.emil.claim_service.service.FunnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funnels")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FunnelController {

    private final FunnelService funnelService;

    @GetMapping("/{slug}")
    public ResponseEntity<FunnelResponse> getFunnel(@PathVariable String slug) {
        return ResponseEntity.ok(funnelService.getFunnelBySlug(slug));
    }

    @PutMapping("/{slug}")
    public ResponseEntity<FunnelResponse> updateFunnel(
            @PathVariable String slug,
            @RequestBody UpdateFunnelRequest request) {
        return ResponseEntity.ok(funnelService.updateOrCreateFunnel(slug, request));
    }
}