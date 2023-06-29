package com.stubee.rollinginfrastructure.common.feign.naver.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.stubee.rollinginfrastructure.common.feign.naver.client")
public class FeignClientConfig {
}