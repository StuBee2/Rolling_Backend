package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryReviewWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryReviewListByMemberUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewListByMemberApi implements QueryReviewListByMemberUseCase {

    private final QueryReviewWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(final UUID memberId, PageRequest pageRequest) {
        return queryReviewWithPaginationPort.getByMemberId(memberId, pageRequest);
    }

}