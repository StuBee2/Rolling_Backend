package com.stubee.reviewapplication.outports.command;

import com.stubee.rollingdomains.domain.review.model.ReviewId;

public interface DeleteReviewPort {

    void deleteById(ReviewId reviewId);

}