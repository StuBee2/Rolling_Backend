package com.stubee.reviewapplication.outports.command;

import com.stubee.rollingdomains.domain.review.model.Review;

public interface RegisterReviewPort {

    Review register(Review review);

}