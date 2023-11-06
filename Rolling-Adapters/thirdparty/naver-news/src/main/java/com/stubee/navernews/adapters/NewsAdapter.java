package com.stubee.navernews.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.rollingdomains.common.model.dtos.request.PageRequest;
import com.stubee.navernews.exception.NewsClientException;
import com.stubee.newsapplication.outports.NewsPort;
import com.stubee.newsapplication.usecases.response.NaverNewsResponse;
import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Adapter
@RequiredArgsConstructor
public class NewsAdapter implements NewsPort {

    private final WebClient newsClient;

    @Override
    public Mono<NaverNewsResponse> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        final int display = Math.toIntExact(pageRequest.size());
        final int start = (Math.toIntExact(pageRequest.page())-1)*display+1;

        return fetchNews(companyName, display, start)
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
                .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR)))
                .bodyToMono(NaverNewsResponse.class);
    }

}