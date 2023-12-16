package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryStoryWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryStoryListByMemberUseCase;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
class QueryStoryListByMemberApi implements QueryStoryListByMemberUseCase {

    private final QueryStoryWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<StoryQueryByMemberResponse>> get(final Long memberId, PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryReviewWithPaginationPort.findByMemberId(memberId, pageRequest));
    }

}