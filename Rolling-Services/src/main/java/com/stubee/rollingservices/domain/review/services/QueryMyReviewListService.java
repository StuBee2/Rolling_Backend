package com.stubee.rollingservices.domain.review.services;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import com.stubee.rollingusecases.domain.review.usecases.query.QueryMyReviewListUseCase;
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