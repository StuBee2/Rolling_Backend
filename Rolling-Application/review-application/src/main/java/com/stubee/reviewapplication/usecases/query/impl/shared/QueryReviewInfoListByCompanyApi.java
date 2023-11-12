package com.stubee.reviewapplication.usecases.query.impl.shared;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryStoryWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryStoryInfoListByCompanyUseCase;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryReviewInfoListByCompanyApi implements QueryStoryInfoListByCompanyUseCase {

    private final QueryStoryWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<StoryQueryByCompanyResponse>> get(Long companyId, PageRequest pageRequest) {
        return PageDataResponse.of(queryReviewWithPaginationPort.findByCompanyId(companyId, pageRequest));
    }

}