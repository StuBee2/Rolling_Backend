package com.stubee.rollingapplication.domain.news.service;

import com.stubee.rollingapplication.domain.news.port.api.NewsUseCase;
import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class NewsService implements NewsUseCase {

    private final NewsPort newsPort;

    @Async
    @Override
    public CompletableFuture<?> getNewsByCompanyName(final String companyName, final PageRequest pageRequest) {
        return newsPort.getByCompanyName(companyName, pageRequest);
    }

}