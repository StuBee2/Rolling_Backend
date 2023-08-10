package com.stubee.rollingadapters.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.stubee.rollingexternal")
public class EnableRepositoryConfig {
}