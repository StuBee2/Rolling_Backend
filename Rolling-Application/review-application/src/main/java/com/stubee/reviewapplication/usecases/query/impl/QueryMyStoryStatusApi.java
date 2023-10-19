package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.reviewapplication.outports.query.QueryStoryStatusPort;
import com.stubee.reviewapplication.usecases.query.QueryMyStoryStatusUseCase;
import com.stubee.reviewapplication.usecases.query.response.StoryStatusResponse;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyStoryStatusApi implements QueryMyStoryStatusUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final QueryStoryStatusPort queryReviewStatusPort;

    @Override
    public StoryStatusResponse get() {
        final Long memberId = getCurrentMemberPort.getMemberId().getId();

        return queryReviewStatusPort.getStatusByMemberId(memberId);
    }

}