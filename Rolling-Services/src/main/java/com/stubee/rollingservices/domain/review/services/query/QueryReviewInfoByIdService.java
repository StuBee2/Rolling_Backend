package com.stubee.rollingservices.domain.review.services.query;

import com.stubee.rollingservices.common.annotations.QueryService;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import com.stubee.rollingusecases.domain.review.usecases.query.QueryReviewInfoByIdUseCase;
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