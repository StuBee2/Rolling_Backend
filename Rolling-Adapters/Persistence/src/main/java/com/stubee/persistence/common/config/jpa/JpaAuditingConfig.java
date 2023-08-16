package com.stubee.persistence.common.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan("com.stubee.persistence")
@EnableJpaRepositories("com.stubee.persistence")
public class JpaAuditingConfig {
}