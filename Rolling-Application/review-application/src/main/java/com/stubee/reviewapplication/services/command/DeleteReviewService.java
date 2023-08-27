package com.stubee.reviewapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.member.LoadCurrentMemberPort;
import com.stubee.reviewapplication.commands.DeleteReviewCommand;
import com.stubee.reviewapplication.outports.CommandReviewPort;
import com.stubee.reviewapplication.outports.QueryReviewPort;
import com.stubee.reviewapplication.usecases.command.DeleteReviewUseCase;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteReviewService implements DeleteReviewUseCase {

    private final LoadCurrentMemberPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public void delete(DeleteReviewCommand command) {
        Review review = queryReviewPort.findById(command.reviewId().getId())
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.isAuthor(memberSecurityPort.getCurrentMemberId());

        commandReviewPort.deleteById(review.reviewId());
    }

}