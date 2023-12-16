package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyExistenceService;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.services.RegisterStoryService;
import com.stubee.reviewapplication.usecases.command.RegisterStoryCommand;
import com.stubee.reviewapplication.usecases.command.RegisterStoryUseCase;
import com.stubee.rollingdomains.domain.story.model.Story;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
class RegisterStoryApi implements RegisterStoryUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final GetCurrentMemberPort getCurrentMemberPort;
    private final RegisterStoryService registerStoryService;

    @Override
    public Story register(final RegisterStoryCommand command) {
        checkCompanyExistenceService.checkById(command.companyId());

        final MemberId memberId = getCurrentMemberPort.getMemberId();

        final Story story = StoryMapper.toDomain(command, memberId);

        return registerStoryService.register(story);
    }

}

