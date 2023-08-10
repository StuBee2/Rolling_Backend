package com.stubee.rollingadapters.domain.news.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingexternal.thirdparty.news.client.NewsClient;
import com.stubee.rollingports.domain.news.ports.NewsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Adapter
@Slf4j
@RequiredArgsConstructor
public class NewsAdapter implements NewsPort {

    private final NewsClient newsClient;

    @Override
    public Mono<?> getByCompanyName(final String companyName, final PageRequest pageRequest) {
        return newsClient.getByCompanyName(companyName, pageRequest);
    }

}