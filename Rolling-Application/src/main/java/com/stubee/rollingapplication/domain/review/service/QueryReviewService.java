package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.review.exceptionn.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewService implements QueryReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public ReviewInfoResponse getInfo(final UUID reviewId) {
        return queryReviewPort.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

    @Override
    public List<ReviewQueryResponse> getMy() {
        return getByMemberId(memberSecurityPort.getCurrentMember().id());
    }

    @Override
    public List<ReviewQueryResponse> getByMemberId(final UUID memberId) {
        return queryReviewPort.findByMemberId(memberId);
    }

}