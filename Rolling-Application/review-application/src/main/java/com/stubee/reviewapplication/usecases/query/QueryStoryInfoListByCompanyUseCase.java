package com.stubee.reviewapplication.usecases.query;

import com.stubee.applicationcommons.model.response.PageDataResponse;
import com.stubee.rollingdomains.common.model.PageRequest;

import java.util.List;

public interface QueryStoryInfoListByCompanyUseCase {

    PageDataResponse<List<StoryQueryByCompanyResponse>> get(Long companyId, PageRequest pageRequest);

}