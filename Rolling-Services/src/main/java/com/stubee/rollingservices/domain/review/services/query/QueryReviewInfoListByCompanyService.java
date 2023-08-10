package com.stubee.rollingservices.domain.review.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import com.stubee.rollingusecases.domain.review.usecases.query.QueryReviewInfoListByCompanyUseCase;
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