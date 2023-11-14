package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.reviewapplication.outports.query.QueryStoryWithPaginationPort;
import com.stubee.reviewapplication.usecases.query.QueryMyStoryListUseCase;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyStoryListApi implements QueryMyStoryListUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final QueryStoryWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<StoryQueryByMemberResponse>> get(final PageRequest pageRequest) {
        final Long memberId = getCurrentMemberPort.getMemberId().getId();

        return PageDataResponse.of(queryReviewWithPaginationPort.findByMemberId(memberId, pageRequest));
    }

}