package com.stubee.rollingdomains.domain.review.services.commands;

import com.stubee.rollingdomains.domain.review.model.ReviewId;

import java.util.UUID;

public record DeleteReviewCommand(
        ReviewId reviewId) {
    public static DeleteReviewCommand toCommand(final UUID companyId) {
        return new DeleteReviewCommand(ReviewId.of(companyId));
    }
}