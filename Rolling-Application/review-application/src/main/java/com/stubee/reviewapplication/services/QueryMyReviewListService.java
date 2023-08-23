package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewListUseCase;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
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
                memberSecurityPort.getCurrentMemberId().getId(), pageRequest));
    }

}