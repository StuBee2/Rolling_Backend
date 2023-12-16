package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.services.DeleteStoryService;
import com.stubee.reviewapplication.usecases.command.DeleteStoryCommand;
import com.stubee.reviewapplication.usecases.command.DeleteStoryUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
class DeleteStoryApi implements DeleteStoryUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final DeleteStoryService deleteReviewService;

    @Override
    public void delete(final DeleteStoryCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        deleteReviewService.delete(command.storyId(), memberId);
    }

}