package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryReviewStatusPort;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewStatusUseCase;
import com.stubee.reviewapplication.usecases.query.response.ReviewStatusResponse;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewStatusApi implements QueryMyReviewStatusUseCase {

    private final GetMemberInfoService getMemberInfoService;
    private final QueryReviewStatusPort queryReviewStatusPort;

    @Override
    public ReviewStatusResponse get() {
        final UUID memberId = getMemberInfoService.getMemberId().getId();

        return queryReviewStatusPort.findByMemberId(memberId);
    }

}