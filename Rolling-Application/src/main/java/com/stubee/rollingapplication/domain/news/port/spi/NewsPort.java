package com.stubee.rollingapplication.domain.news.port.spi;

import com.stubee.rollingcore.domain.news.dto.response.NewsResponse;

public interface NewsPort {

    NewsResponse getByCompanyName(String companyName, int size, int page);

}