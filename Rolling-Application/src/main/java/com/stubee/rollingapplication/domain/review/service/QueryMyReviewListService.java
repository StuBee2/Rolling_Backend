package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.query.QueryMyReviewListUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewListService implements QueryMyReviewListUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByMemberId(
                memberSecurityPort.getCurrentMemberId().id(), pageRequest));
    }

}