package com.emil.claim_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "funnel_configs")
@Getter
@Setter
public class FunnelConfig {
    @Id
    private String slug;
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "config_json")
    private Map<String, Object> config;
}