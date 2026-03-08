package com.emil.claim_service.mapper;


import com.emil.claim_service.dto.request.CreateClaimRequest;
import com.emil.claim_service.dto.response.ClaimResponse;
import com.emil.claim_service.entity.Claim;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    @Mapping(source = "user.username", target = "username")
    ClaimResponse toResponse(Claim claim);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "claimNumber", ignore = true)
    @Mapping(target = "status", constant = "OPEN")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Claim toEntity(CreateClaimRequest request);
}