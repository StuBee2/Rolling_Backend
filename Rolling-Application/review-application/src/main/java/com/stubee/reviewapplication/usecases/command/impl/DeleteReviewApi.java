package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.services.DeleteReviewService;
import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;
import com.stubee.reviewapplication.usecases.command.DeleteReviewUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteReviewApi implements DeleteReviewUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final DeleteReviewService deleteReviewService;

    @Override
    public void delete(final DeleteReviewCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        deleteReviewService.delete(command, memberId);
    }

}