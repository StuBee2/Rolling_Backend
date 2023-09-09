package com.stubee.rollingdomains.domain.review.services.commands;

import com.stubee.rollingdomains.domain.review.model.ReviewId;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DeleteReviewCommand(
        ReviewId reviewId) {
    public static DeleteReviewCommand toCommand(final UUID companyId) {
        return DeleteReviewCommand.builder()
                .reviewId(ReviewId.create(companyId))
                .build();
    }
}