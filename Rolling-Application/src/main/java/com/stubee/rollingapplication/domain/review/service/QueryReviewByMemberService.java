package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewByMemberUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewByMemberService implements QueryReviewByMemberUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> getMy(PageRequest pageRequest) {
        return getByMemberId(memberSecurityPort.getCurrentMemberId().id(), pageRequest);
    }

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> getByMemberId(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByMemberId(memberId, pageRequest));
    }

}