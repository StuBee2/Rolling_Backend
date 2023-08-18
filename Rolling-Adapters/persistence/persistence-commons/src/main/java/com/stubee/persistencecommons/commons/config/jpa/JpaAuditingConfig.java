package com.stubee.persistencecommons.commons.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan("com.stubee.persistencecommons.commons.entity")
@EnableJpaRepositories("com.stubee")
public class JpaAuditingConfig {
}