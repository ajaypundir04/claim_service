package com.emil.claim_service.dto.request;

import com.emil.claim_service.validator.ValidRegisterUser;
import lombok.Data;

@Data
@ValidRegisterUser
public class RegisterUserRequest {

    private String name;
    private String email;
    private String username;

}