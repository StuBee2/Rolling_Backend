package com.stubee.reviewapplication.outports.query;

import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import com.stubee.rollingdomains.domain.review.model.Review;

import java.util.Optional;
import java.util.UUID;

public interface QueryReviewByIdPort {

    Optional<Review> findById(UUID id);

    Optional<ReviewInfoResponse> findInfoById(UUID id);

}