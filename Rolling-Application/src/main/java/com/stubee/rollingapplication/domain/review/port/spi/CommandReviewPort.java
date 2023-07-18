package com.stubee.rollingapplication.domain.review.port.spi;

import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewId;

public interface CommandReviewPort {

    Review save(Review review);

    void deleteById(ReviewId reviewId);

}