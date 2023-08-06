package com.stubee.rollinginfrastructure.thirdparty.naver.adapter;

import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollinginfrastructure.thirdparty.naver.response.NaverNewsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewsAdapter implements NewsPort {

    private final WebClient newsWebClient;

    @Async
    @Override
    public CompletableFuture<?> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        final String encodedName = encodeCompanyName(companyName);
        final String sort = "date";
        final int display = Math.toIntExact(pageRequest.size());
        final int start = (Math.toIntExact(pageRequest.page())-1)*display+1;

        return newsWebClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("query", encodedName)
                                .queryParam("display", display)
                                .queryParam("start", start)
                                .queryParam("sort", sort)
                                .build())
                        .retrieve()
                        .bodyToMono(NaverNewsResponse.class)
                .toFuture();
    }

    private String encodeCompanyName(final String companyName) {
        return URLEncoder.encode(companyName, StandardCharsets.UTF_8);
    }

}