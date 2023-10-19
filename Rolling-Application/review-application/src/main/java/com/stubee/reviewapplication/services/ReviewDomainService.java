package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.reviewapplication.outports.command.CommandReviewPort;
import com.stubee.reviewapplication.outports.query.QueryReviewByIdPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.services.DeleteReviewService;
import com.stubee.rollingdomains.domain.review.services.RegisterReviewService;
import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;
import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class ReviewDomainService implements RegisterReviewService, DeleteReviewService {

    private final CommandReviewPort commandReviewPort;
    private final QueryReviewByIdPort queryReviewByIdPort;

    @Override
    public Review register(final RegisterReviewCommand command, final MemberId memberId) {
        return commandReviewPort.register(command.toDomain(memberId));
    }

    @Override
    public void delete(final DeleteReviewCommand command, final MemberId memberId) {
        final Review review = this.getById(command.reviewId().getId());

        review.isAuthor(memberId);

        commandReviewPort.deleteById(command.reviewId());
    }

    private Review getById(final UUID id) {
        return queryReviewByIdPort.findById(id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

}