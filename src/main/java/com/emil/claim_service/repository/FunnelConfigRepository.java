package com.emil.claim_service.repository;

import com.emil.claim_service.entity.FunnelConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunnelConfigRepository extends JpaRepository<FunnelConfig, String> {

}