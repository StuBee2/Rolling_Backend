package com.stubee.rollingexternal.thirdparty.news.client;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsClient {

    Mono<?> getByCompanyName(String companyName, PageRequest pageRequest);

}