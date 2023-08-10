package com.stubee.rollingusecases.domain.review.usecases.query;

import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;

import java.util.UUID;

public interface QueryReviewInfoByIdUseCase {

    ReviewInfoResponse get(UUID reviewId);

}