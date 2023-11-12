package com.stubee.newsapplication.usecases;

import com.stubee.rollingdomains.common.model.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsUseCase {

    Mono<?> getNewsByCompanyName(String companyName, PageRequest pageRequest);

}