package com.stubee.rollingapplication.domain.review.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.review.port.api.query.QueryReviewListByMemberUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;
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