package com.emil.claim_service.dto.response;


import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String name;

    private String email;
}