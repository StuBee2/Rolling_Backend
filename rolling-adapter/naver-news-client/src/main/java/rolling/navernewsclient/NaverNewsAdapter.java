package rolling.navernewsclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import rolling.application.news.interactor.NaverNewsResponse;
import rolling.application.news.outport.NewsPort;
import rolling.domain.common.error.exception.InternalServerException;
import rolling.domain.common.model.PageRequest;

@Component
@RequiredArgsConstructor
class NaverNewsAdapter implements NewsPort {

    private final WebClient webClient;

    @Override
    public Mono<NaverNewsResponse> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        final int display = Math.toIntExact(pageRequest.size());
        final int start = (Math.toIntExact(pageRequest.page())-1)*display+1;

        return fetchNews(companyName, display, start)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<NaverNewsResponse> fetchNews(final String encodedName, int display, int start) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", encodedName)
                        .queryParam("display", display)
                        .queryParam("start", start)
                        .queryParam("sort", "date")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(NewsClientException.EXCEPTION))
                .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(InternalServerException.EXCEPTION))
                .bodyToMono(NaverNewsResponse.class);
    }

}