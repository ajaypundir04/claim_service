package com.emil.claim_service.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class FunnelResponse {
    private String slug;
    private String name;
    private Map<String, Object> config;
}