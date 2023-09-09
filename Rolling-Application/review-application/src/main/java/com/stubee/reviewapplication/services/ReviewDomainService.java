package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.reviewapplication.outports.command.DeleteReviewPort;
import com.stubee.reviewapplication.outports.command.RegisterReviewPort;
import com.stubee.reviewapplication.outports.query.QueryReviewByIdPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.services.DeleteReviewService;
import com.stubee.rollingdomains.domain.review.services.RegisterReviewService;
import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;
import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ReviewDomainService implements RegisterReviewService, DeleteReviewService {

    private final RegisterReviewPort registerReviewPort;
    private final DeleteReviewPort deleteReviewPort;
    private final QueryReviewByIdPort queryReviewByIdPort;

    @Override
    public Review register(final RegisterReviewCommand command, final MemberId memberId) {
        return registerReviewPort.register(command.toDomain(memberId));
    }

    @Override
    public void delete(final DeleteReviewCommand command, final MemberId memberId) {
        final Review review = queryReviewByIdPort.findById(command.reviewId().getId())
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.isAuthor(memberId);

        deleteReviewPort.deleteById(command.reviewId());
    }

}