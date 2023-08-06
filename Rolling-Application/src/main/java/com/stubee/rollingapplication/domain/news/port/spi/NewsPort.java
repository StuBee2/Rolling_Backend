package com.stubee.rollingapplication.domain.news.port.spi;

import com.stubee.rollingcore.common.dto.request.PageRequest;

import java.util.concurrent.CompletableFuture;

public interface NewsPort {

    CompletableFuture<?> getByCompanyName(String companyName, PageRequest pageRequest);

}