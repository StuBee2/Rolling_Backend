package com.stubee.reviewapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoListByCompanyUseCase;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewInfoListByCompanyService implements QueryReviewInfoListByCompanyUseCase {

    private final QueryReviewPort queryReviewPort;

    @Override
    public PageDataResponse<List<ReviewInfoResponse>> get(UUID companyId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByCompanyId(companyId, pageRequest));
    }

}