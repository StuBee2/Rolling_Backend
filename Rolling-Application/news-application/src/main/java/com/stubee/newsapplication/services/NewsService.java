package com.stubee.newsapplication.services;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.newsapplication.outports.NewsPort;
import com.stubee.newsapplication.usecases.NewsUseCase;
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