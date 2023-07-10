package com.stubee.rollingapplication.domain.news.port.spi;

import com.stubee.rollingcore.common.dto.response.PageDataResponse;

public interface NewsPort {

    PageDataResponse getByCompanyName(String companyName, int size, int page);

}