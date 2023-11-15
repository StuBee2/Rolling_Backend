package com.stubee.newsapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.ExternalService;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.newsapplication.outports.NewsPort;
import com.stubee.newsapplication.usecases.NewsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@ExternalService
@RequiredArgsConstructor
public class NewsApi implements NewsUseCase {

    private final NewsPort newsPort;

    @Override
    public Mono<?> getNewsByCompanyName(final String companyName, final PageRequest pageRequest) {
        pageRequest.validate();

        return newsPort.getByCompanyName(companyName, pageRequest);
    }

}