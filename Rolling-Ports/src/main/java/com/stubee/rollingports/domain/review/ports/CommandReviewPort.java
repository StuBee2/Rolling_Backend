package com.stubee.rollingports.domain.review.ports;

import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.model.ReviewId;

public interface CommandReviewPort {

    Review save(Review review);

    void deleteById(ReviewId reviewId);

}