package com.emil.claim_service.web;

import com.emil.claim_service.dto.request.RegisterUserRequest;
import com.emil.claim_service.dto.response.UserResponse;
import com.emil.claim_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(
            @Valid @RequestBody RegisterUserRequest request
    ) {
        return userService.register(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsernames() {
        return userService.getAllUsernames();
    }
}