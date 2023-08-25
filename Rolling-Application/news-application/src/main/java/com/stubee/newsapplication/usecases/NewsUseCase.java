package com.stubee.newsapplication.usecases;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import reactor.core.publisher.Mono;

public interface NewsUseCase {

    Mono<?> getNewsByCompanyName(String companyName, PageRequest pageRequest);

}