package com.stubee.rollingapplication.domain.review.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewService implements QueryReviewUseCase {

    private final QueryReviewPort queryReviewPort;

    @Override
    public ReviewInfoResponse getInfo(final UUID reviewId) {
        return queryReviewPort.findInfoById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

    @Override
    public PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(final UUID companyId, PageRequest pageRequest) {
        return PageDataResponse.create(queryReviewPort.findByCompanyId(companyId, pageRequest));
    }

}