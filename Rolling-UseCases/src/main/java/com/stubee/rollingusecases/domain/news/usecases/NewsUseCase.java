package com.stubee.rollingusecases.domain.news.usecases;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsUseCase {

    Mono<?> getNewsByCompanyName(String companyName, PageRequest pageRequest);

}