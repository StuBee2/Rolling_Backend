package com.stubee.thirdparty.common.config.webclient;

import com.stubee.thirdparty.common.properties.news.NaverProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final NaverProperties naverProperties;

    @Bean
    WebClient newsClient() {
        return WebClient.builder()
                .baseUrl(naverProperties.getBaseUrl())
                .defaultHeader("X-Naver-Client-Id", naverProperties.getClientId())
                .defaultHeader("X-Naver-Client-Secret", naverProperties.getClientSecret())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}