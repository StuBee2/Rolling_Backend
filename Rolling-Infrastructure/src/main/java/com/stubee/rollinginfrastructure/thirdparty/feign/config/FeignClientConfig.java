package com.stubee.rollinginfrastructure.thirdparty.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.stubee.rollinginfrastructure.thirdparty.feign")
public class FeignClientConfig {
}