package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyExistenceService;
import com.stubee.rollingdomains.domain.employment.services.CheckEmploymentExistenceService;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.services.RegisterReviewService;
import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import com.stubee.reviewapplication.usecases.command.RegisterReviewUseCase;
import com.stubee.rollingdomains.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterReviewApi implements RegisterReviewUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final CheckEmploymentExistenceService checkEmploymentExistenceService;
    private final GetCurrentMemberPort getCurrentMemberPort;
    private final RegisterReviewService registerReviewService;

    @Override
    public Review register(final RegisterReviewCommand command) {
        checkCompanyExistenceService.checkById(command.companyId());

        final MemberId memberId = getCurrentMemberPort.getMemberId();

        checkEmploymentExistenceService.checkByEmployeeAndEmployer(memberId.getId(), command.companyId());

        return registerReviewService.register(command, memberId);
    }

}