package com.stubee.rollingexternal.global.config.webclient;

import com.stubee.rollingexternal.thirdparty.news.properties.NaverProperties;
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
    WebClient newsWebClient() {
        return WebClient.builder()
                .baseUrl("https://openapi.naver.com/v1/search/news.json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Naver-Client-Id", naverProperties.getClientId())
                .defaultHeader("X-Naver-Client-Secret", naverProperties.getClientSecret())
                .build();
    }

}