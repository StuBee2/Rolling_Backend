package com.stubee.rollingservices.domain.review.services.command;

import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingports.domain.review.ports.CommandReviewPort;
import com.stubee.rollingusecases.domain.review.commands.WriteReviewCommand;
import com.stubee.rollingusecases.domain.review.usecases.command.WriteReviewUseCase;
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