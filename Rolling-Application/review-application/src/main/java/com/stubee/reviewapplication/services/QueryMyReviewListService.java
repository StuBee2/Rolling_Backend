package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplicationshared.ports.LoadCurrentMemberPort;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;
import com.stubee.reviewapplication.usecases.query.QueryMyReviewListUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyReviewListService implements QueryMyReviewListUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByMemberId(
                loadCurrentMemberPort.getMemberId().getId(), pageRequest));
    }

}