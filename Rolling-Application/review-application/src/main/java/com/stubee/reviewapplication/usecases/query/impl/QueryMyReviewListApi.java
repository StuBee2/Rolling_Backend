package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryReviewWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewListUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewListApi implements QueryMyReviewListUseCase {

    private final GetMemberInfoService queryMemberInfoService;
    private final QueryReviewWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(final PageRequest pageRequest) {
        final UUID memberId = queryMemberInfoService.getMemberId().getId();

        return queryReviewWithPaginationPort.getByMemberId(memberId, pageRequest);
    }

}