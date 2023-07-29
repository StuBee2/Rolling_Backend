package com.stubee.rollingapplication.domain.review.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.command.WriteReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.review.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class WriteReviewService implements WriteReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public Review write(WriteReviewCommand command) {
        doesCompanyExists(command.companyId());

        return commandReviewPort.save(createExceptReviewId(command, memberSecurityPort.getCurrentMemberId()));
    }

    private void doesCompanyExists(final UUID companyId) {
        if(queryCompanyPort.existsByCompanyId(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

    private Review createExceptReviewId(WriteReviewCommand command, MemberId memberId) {
        return Review.create(command.content(), command.position(), command.careerPath(), command.salaryAndBenefits(),
                command.workLifeBalance(), command.organizationalCulture(), command.careerAdvancement(),
                command.companyId(), memberId);
    }

}