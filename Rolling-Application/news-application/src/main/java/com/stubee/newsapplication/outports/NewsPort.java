package com.stubee.newsapplication.outports;

import com.stubee.rollingdomains.common.model.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsPort {

    Mono<?> getByCompanyName(String companyName, PageRequest pageRequest);

}