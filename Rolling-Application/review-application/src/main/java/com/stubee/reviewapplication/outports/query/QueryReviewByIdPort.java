package com.stubee.reviewapplication.outports.query;

import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;

import java.util.Optional;
import java.util.UUID;

public interface QueryReviewByIdPort {

    Optional<Review> findById(UUID id);

    Optional<ReviewInfoResponse> findInfoById(UUID id);

    default ReviewInfoResponse getInfoById(final UUID id) {
        return findInfoById(id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

}