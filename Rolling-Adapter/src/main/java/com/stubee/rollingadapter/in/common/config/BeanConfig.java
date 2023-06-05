package com.stubee.rollingadapter.in.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.stubee.rollingapplication", "com.stubee.rollinginfrastructure"})
public class BeanConfig {
}