package com.stubee.reviewapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.reviewapplication.commands.DeleteReviewCommand;
import com.stubee.reviewapplication.outports.CommandReviewPort;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.usecases.command.DeleteReviewUseCase;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteReviewService implements DeleteReviewUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public void delete(final DeleteReviewCommand command) {
        final Review review = queryReviewPort.findById(command.reviewId().getId())
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.isAuthor(loadCurrentMemberPort.getMemberId());

        commandReviewPort.deleteById(review.reviewId());
    }

}