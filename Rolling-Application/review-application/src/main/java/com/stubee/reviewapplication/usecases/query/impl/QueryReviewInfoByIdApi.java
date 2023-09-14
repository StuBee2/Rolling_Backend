package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryReviewByIdPort;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoByIdUseCase;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewInfoByIdApi implements QueryReviewInfoByIdUseCase {

    private final QueryReviewByIdPort queryReviewByIdPort;

    @Override
    public ReviewInfoResponse get(final UUID reviewId) {
        return queryReviewByIdPort.getInfoById(reviewId);
    }

}