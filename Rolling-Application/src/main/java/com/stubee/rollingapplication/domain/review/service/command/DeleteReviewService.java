package com.stubee.rollingapplication.domain.review.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.command.DeleteReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.review.command.DeleteReviewCommand;
import com.stubee.rollingcore.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingcore.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteReviewService implements DeleteReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public void delete(DeleteReviewCommand command) {
        Review review = queryReviewPort.findById(command.reviewId().id())
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.isAuthor(memberSecurityPort.getCurrentMemberId());

        commandReviewPort.deleteById(review.reviewId());
    }

}