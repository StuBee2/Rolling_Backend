package com.stubee.rollinginfrastructure.thirdparty.naver.adapter;

import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollinginfrastructure.global.exception.news.NewsClientException;
import com.stubee.rollinginfrastructure.thirdparty.naver.response.NaverNewsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Adapter
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
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(NewsClientException.EXCEPTION))
                .bodyToMono(NaverNewsResponse.class)
                .toFuture();
    }

    private String encodeCompanyName(final String companyName) {
        return URLEncoder.encode(companyName, StandardCharsets.UTF_8);
    }

}