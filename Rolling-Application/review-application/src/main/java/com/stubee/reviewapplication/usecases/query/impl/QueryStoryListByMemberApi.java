package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryStoryWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryStoryListByMemberUseCase;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryStoryListByMemberApi implements QueryStoryListByMemberUseCase {

    private final QueryStoryWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<StoryQueryByMemberResponse>> get(final Long memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewWithPaginationPort.findByMemberId(memberId, pageRequest));
    }

}