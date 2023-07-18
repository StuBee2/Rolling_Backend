package com.stubee.rollingapplication.domain.review.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.CommandReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.review.command.DeleteReviewCommand;
import com.stubee.rollingcore.domain.review.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.exception.ReviewNotFoundException;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewId;
import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class CommandReviewService implements CommandReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public Review write(WriteReviewCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        if(isNotFound(command.companyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        return commandReviewPort.save(createExceptReviewId(command, member.memberId()));
    }

    @Override
    public void delete(DeleteReviewCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        ReviewInfoResponse reviewInfo = queryReviewPort.findInfoById(command.reviewId().id())
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        reviewInfo.isAuthor(member.memberId());

        commandReviewPort.deleteById(ReviewId.create(reviewInfo.reviewId()));
    }

    private boolean isNotFound(final UUID companyId) {
        return queryCompanyPort.existsByCompanyId(companyId);
    }

    private Review createExceptReviewId(WriteReviewCommand command, MemberId memberId) {
        return Review.create(command.content(), command.position(), command.careerPath(), command.salaryAndBenefits(),
                command.workLifeBalance(), command.organizationalCulture(), command.careerAdvancement(),
                command.companyId(), memberId);
    }

}