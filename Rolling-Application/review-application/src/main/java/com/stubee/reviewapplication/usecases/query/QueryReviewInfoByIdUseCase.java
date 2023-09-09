package com.stubee.reviewapplication.usecases.query;

import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;

import java.util.UUID;

public interface QueryReviewInfoByIdUseCase {

    ReviewInfoResponse get(UUID reviewId);

}