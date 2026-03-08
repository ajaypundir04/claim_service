package com.emil.claim_service.service;

import com.emil.claim_service.dto.request.RegisterUserRequest;
import com.emil.claim_service.dto.response.UserResponse;
import com.emil.claim_service.entity.User;
import com.emil.claim_service.mapper.UserMapper;
import com.emil.claim_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void register_ShouldReturnUserResponse() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        UserResponse response = new UserResponse();
        response.setUsername("testuser");
        response.setEmail("test@example.com");

        when(userMapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(response);

        UserResponse result = userService.register(request);

        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void getAllUsernames_ShouldReturnListOfUserResponses() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        UserResponse response = new UserResponse();
        response.setUsername("testuser");
        response.setEmail("test@example.com");

        List<User> users = Collections.singletonList(user);
        List<UserResponse> responses = Collections.singletonList(response);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toResponse(user)).thenReturn(response);

        List<UserResponse> result = userService.getAllUsernames();

        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
        assertEquals("test@example.com", result.get(0).getEmail());
    }
}
