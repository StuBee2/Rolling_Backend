package com.stubee.rollingapplication.domain.review.port.api.query;

import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;

import java.util.UUID;

public interface QueryReviewInfoByIdUseCase {

    ReviewInfoResponse get(UUID reviewId);

}