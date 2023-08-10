package com.stubee.rollingbatch.common.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {
        "com.stubee.rollingservices.domain.company.services.command",
        "com.stubee.rollingservices.domain.company.services.query",
        "com.stubee.rollingservices.domain.review.services.query",
        "com.stubee.rollingadapters.domain.company",
        "com.stubee.rollingadapters.domain.review",
        "com.stubee.rollingadapters.global",
        "com.stubee.rollingexternal.persistence",
        "com.stubee.rollingexternal.global.config"
})
public class BatchConfig {
}