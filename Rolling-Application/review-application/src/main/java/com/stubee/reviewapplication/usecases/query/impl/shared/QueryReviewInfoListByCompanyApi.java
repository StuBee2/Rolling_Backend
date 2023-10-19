package com.stubee.reviewapplication.usecases.query.impl.shared;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryStoryWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryStoryInfoListByCompanyUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryReviewInfoListByCompanyApi implements QueryStoryInfoListByCompanyUseCase {

    private final QueryStoryWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<StoryQueryByCompanyResponse>> get(Long companyId, PageRequest pageRequest) {
        return queryReviewWithPaginationPort.getByCompanyId(companyId, pageRequest);
    }

}