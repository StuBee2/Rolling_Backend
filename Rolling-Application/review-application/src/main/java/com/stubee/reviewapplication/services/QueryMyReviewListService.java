package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryReviewWithPaginationPort;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewListUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewListService implements QueryMyReviewListUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final QueryReviewWithPaginationPort queryReviewWithPaginationPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(final PageRequest pageRequest) {
        final UUID memberId = loadCurrentMemberPort.getMemberId().getId();

        return PageDataResponse.create(queryReviewWithPaginationPort.findByMemberId(memberId, pageRequest));
    }

}