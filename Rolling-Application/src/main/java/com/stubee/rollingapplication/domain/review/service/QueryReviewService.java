package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.common.dto.request.PageRequest;
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
    public PageDataResponse<List<ReviewQueryResponse>> getMy(PageRequest pageRequest) {
        return getByMemberId(memberSecurityPort.getCurrentMember().memberId().id(), pageRequest);
    }

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> getByMemberId(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByMemberId(memberId, pageRequest));
    }

    @Override
    public PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(final UUID companyId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByCompanyId(companyId, pageRequest));
    }

}