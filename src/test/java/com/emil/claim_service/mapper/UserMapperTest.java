package com.emil.claim_service.mapper;

import com.emil.claim_service.dto.request.RegisterUserRequest;
import com.emil.claim_service.dto.response.UserResponse;
import com.emil.claim_service.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toResponse_ShouldMapUserToUserResponse() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setName("Test User");

        UserResponse response = userMapper.toResponse(user);

        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("Test User", response.getName());
    }

    @Test
    void toEntity_ShouldMapRegisterUserRequestToUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setName("Test User");

        User user = userMapper.toEntity(request);

        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
    }
}
