package com.stubee.rollingservices.domain.review.services.command;

import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingports.domain.review.ports.CommandReviewPort;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import com.stubee.rollingusecases.domain.review.commands.DeleteReviewCommand;
import com.stubee.rollingusecases.domain.review.usecases.command.DeleteReviewUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteReviewService implements DeleteReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
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