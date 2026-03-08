package com.emil.claim_service.mapper;


import com.emil.claim_service.dto.request.RegisterUserRequest;
import com.emil.claim_service.dto.response.UserResponse;
import com.emil.claim_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntity(RegisterUserRequest request);
}