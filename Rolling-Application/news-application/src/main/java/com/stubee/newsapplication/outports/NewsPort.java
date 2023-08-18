package com.stubee.newsapplication.outports;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsPort {

    Mono<?> getByCompanyName(String companyName, PageRequest pageRequest);

}