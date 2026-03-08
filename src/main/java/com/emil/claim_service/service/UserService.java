package com.emil.claim_service.service;


import com.emil.claim_service.dto.request.RegisterUserRequest;
import com.emil.claim_service.dto.response.UserResponse;
import com.emil.claim_service.entity.User;
import com.emil.claim_service.mapper.UserMapper;
import com.emil.claim_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse register(RegisterUserRequest request) {

        var user = userRepository.save(userMapper.toEntity(request));
        log.info("User Created With Id {}", user.getId());
        return userMapper.toResponse(user);
    }
}