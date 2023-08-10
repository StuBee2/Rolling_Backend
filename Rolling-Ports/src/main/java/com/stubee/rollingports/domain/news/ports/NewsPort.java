package com.stubee.rollingports.domain.news.ports;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsPort {

    Mono<?> getByCompanyName(String companyName, PageRequest pageRequest);

}