package com.stubee.rollingapplication.domain.review.port.spi;

import com.stubee.rollingcore.domain.review.model.Review;

public interface CommandReviewPort {

    Review save(Review review);

}