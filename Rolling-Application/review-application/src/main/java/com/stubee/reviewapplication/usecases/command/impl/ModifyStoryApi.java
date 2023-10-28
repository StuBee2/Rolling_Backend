package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.reviewapplication.usecases.command.ModifyStoryUseCase;
import com.stubee.rollingdomains.domain.story.services.ModifyStoryService;
import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyStoryApi implements ModifyStoryUseCase {

    private final ModifyStoryService modifyStoryService;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public void modify(ModifyStoryCommand command) {
        modifyStoryService.modify(command, getCurrentMemberPort.getMemberId());
    }

}