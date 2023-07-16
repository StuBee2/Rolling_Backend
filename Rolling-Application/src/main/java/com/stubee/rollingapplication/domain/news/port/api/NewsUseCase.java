package com.stubee.rollingapplication.domain.news.port.api;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;

public interface NewsUseCase {

    PageDataResponse<?> getNewsByCompanyName(String companyName, PageRequest pageRequest);

}