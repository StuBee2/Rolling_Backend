package com.stubee.reviewapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.LoadCurrentMemberPort;
import com.stubee.applicationcommons.services.CheckCompanyExistenceService;
import com.stubee.reviewapplication.commands.WriteReviewCommand;
import com.stubee.reviewapplication.outports.CommandReviewPort;
import com.stubee.reviewapplication.usecases.command.WriteReviewUseCase;
import com.stubee.rollingdomains.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class WriteReviewService implements WriteReviewUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandReviewPort commandReviewPort;

    @Override
    public Review write(final WriteReviewCommand command) {
        checkCompanyExistenceService.check(command.companyId());

        return commandReviewPort.save(command.toDomain(loadCurrentMemberPort.getMemberId()));
    }

}