package com.stubee.reviewapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.company.CheckCompanyExistencePort;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.reviewapplication.commands.WriteReviewCommand;
import com.stubee.reviewapplication.outports.CommandReviewPort;
import com.stubee.reviewapplication.usecases.command.WriteReviewUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class WriteReviewService implements WriteReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final CheckCompanyExistencePort checkCompanyExistencePort;

    @Override
    public Review write(WriteReviewCommand command) {
        doesCompanyExists(command.companyId());

        return commandReviewPort.save(createExceptReviewId(command, memberSecurityPort.getCurrentMemberId()));
    }

    private void doesCompanyExists(final UUID companyId) {
        if(checkCompanyExistencePort.check(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }


    private Review createExceptReviewId(WriteReviewCommand command, MemberId memberId) {
        return Review.create(command.content(), command.position(), command.careerPath(), command.salaryAndBenefits(),
                command.workLifeBalance(), command.organizationalCulture(), command.careerAdvancement(),
                command.companyId(), memberId);
    }

}