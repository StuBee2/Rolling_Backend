package com.stubee.reviewapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoByIdUseCase;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
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