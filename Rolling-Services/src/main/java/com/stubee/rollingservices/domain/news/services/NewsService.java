package com.stubee.rollingservices.domain.news.services;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingports.domain.news.ports.NewsPort;
import com.stubee.rollingusecases.domain.news.usecases.NewsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NewsService implements NewsUseCase {

    private final NewsPort newsPort;

    @Override
    public Mono<?> getNewsByCompanyName(final String companyName, final PageRequest pageRequest) {
        return newsPort.getByCompanyName(companyName, pageRequest);
    }

}