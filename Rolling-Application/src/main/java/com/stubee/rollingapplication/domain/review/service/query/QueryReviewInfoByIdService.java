package com.stubee.rollingapplication.domain.review.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.review.port.api.query.QueryReviewInfoByIdUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewInfoByIdService implements QueryReviewInfoByIdUseCase {

    private final QueryReviewPort queryReviewPort;

    @Override
    public ReviewInfoResponse get(final UUID reviewId) {
        return queryReviewPort.findInfoById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

}