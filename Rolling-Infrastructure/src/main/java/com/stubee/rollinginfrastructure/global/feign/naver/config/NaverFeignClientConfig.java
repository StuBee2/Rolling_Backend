package com.stubee.rollinginfrastructure.global.feign.naver.config;

import com.stubee.rollinginfrastructure.global.feign.naver.properties.NaverProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class NaverFeignClientConfig {

    private final NaverProperties naverProperties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return request -> {
            request.header("X-Naver-Client-Id", naverProperties.getClientId());
            request.header("X-Naver-Client-Secret", naverProperties.getClientSecret());
        };
    }

}