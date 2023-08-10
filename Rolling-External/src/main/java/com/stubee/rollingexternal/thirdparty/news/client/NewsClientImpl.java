package com.stubee.rollingexternal.thirdparty.news.client;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingexternal.global.exception.news.NewsClientException;
import com.stubee.rollingdomains.domain.news.response.NaverNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class NewsClientImpl implements NewsClient {

    private final WebClient newsClient;

    @Override
    public Mono<NaverNewsResponse> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        final String encodedName = encodeCompanyName(companyName);
        final String sort = "date";
        final int display = Math.toIntExact(pageRequest.size());
        final int start = (Math.toIntExact(pageRequest.page())-1)*display+1;

        return fetchNewsByCompanyName(encodedName, display, start, sort)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<NaverNewsResponse> fetchNewsByCompanyName(final String encodedName, int display, int start, final String sort) {
        return newsClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", encodedName)
                        .queryParam("display", display)
                        .queryParam("start", start)
                        .queryParam("sort", sort)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(NewsClientException.EXCEPTION))
                .bodyToMono(NaverNewsResponse.class);
    }

    private String encodeCompanyName(final String companyName) {
        return URLEncoder.encode(companyName, StandardCharsets.UTF_8);
    }

}