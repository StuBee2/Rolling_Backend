package com.stubee.thirdparty.news.adapters;

import com.stubee.newsapplication.outports.NewsPort;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.news.response.NaverNewsResponse;
import com.stubee.thirdparty.common.annotations.Adapter;
import com.stubee.thirdparty.news.exception.NewsClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Adapter
@RequiredArgsConstructor
public class NewsAdapter implements NewsPort {

    private final WebClient newsClient;

    @Override
    public Mono<NaverNewsResponse> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        final String encodedName = encodeCompanyName(companyName);
        final int display = Math.toIntExact(pageRequest.size());
        final int start = (Math.toIntExact(pageRequest.page())-1)*display+1;

        return fetchNews(encodedName, display, start)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<NaverNewsResponse> fetchNews(final String encodedName, int display, int start) {
        return newsClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", encodedName)
                        .queryParam("display", display)
                        .queryParam("start", start)
                        .queryParam("sort", "date")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(NewsClientException.EXCEPTION))
                .bodyToMono(NaverNewsResponse.class);
    }

    private String encodeCompanyName(final String companyName) {
        return URLEncoder.encode(companyName, StandardCharsets.UTF_8);
    }

}