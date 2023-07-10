package com.stubee.rollingapplication.domain.news.service;

import com.stubee.rollingapplication.domain.news.port.api.NewsUseCase;
import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService implements NewsUseCase {

    private final NewsPort newsPort;

    @Override
    public PageDataResponse getNewsByCompanyName(final String companyName, PageRequest pageRequest) {
        return newsPort.getByCompanyName(companyName, longToInt(pageRequest.size()), longToInt(pageRequest.page()));
    }

    private int longToInt(long number) {
        return Math.toIntExact(number);
    }

}