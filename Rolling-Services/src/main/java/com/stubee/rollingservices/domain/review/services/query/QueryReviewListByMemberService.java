package com.stubee.rollingservices.domain.review.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import com.stubee.rollingusecases.domain.review.usecases.query.QueryReviewListByMemberUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewListByMemberService implements QueryReviewListByMemberUseCase {

    private final QueryReviewPort queryReviewPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByMemberId(memberId, pageRequest));
    }

}