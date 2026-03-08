package com.emil.claim_service.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Map;

@Data
public class UpdateFunnelRequest {
    @NotBlank(message = "name is required")
    private String name;
    private Map<String, Object> config;
}