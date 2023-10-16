package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.reviewapplication.outports.query.QueryReviewStatusPort;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewStatusUseCase;
import com.stubee.reviewapplication.usecases.query.response.ReviewStatusResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewStatusApi implements QueryMyReviewStatusUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final QueryReviewStatusPort queryReviewStatusPort;

    @Override
    public ReviewStatusResponse get() {
        final UUID memberId = getCurrentMemberPort.getMemberId().getId();

        return queryReviewStatusPort.findByMemberId(memberId);
    }

}