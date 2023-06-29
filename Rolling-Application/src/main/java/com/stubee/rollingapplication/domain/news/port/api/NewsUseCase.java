package com.stubee.rollingapplication.domain.news.port.api;

import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingcore.domain.news.dto.response.NewsResponse;

public interface NewsUseCase {

    NewsResponse getNewsByCompanyName(String companyName, PageRequest pageRequest);

}