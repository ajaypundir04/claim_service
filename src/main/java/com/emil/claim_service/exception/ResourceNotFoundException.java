package com.emil.claim_service.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException  extends ResponseStatusException {
    public ResourceNotFoundException(HttpStatusCode status, @Nullable String reason) {
        super(status, reason);
    }
}
