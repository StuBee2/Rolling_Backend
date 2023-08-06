package com.stubee.rollingapplication.domain.news.port.api;

import com.stubee.rollingcore.common.dto.request.PageRequest;

import java.util.concurrent.CompletableFuture;

public interface NewsUseCase {

    CompletableFuture<?> getNewsByCompanyName(String companyName, PageRequest pageRequest);

}