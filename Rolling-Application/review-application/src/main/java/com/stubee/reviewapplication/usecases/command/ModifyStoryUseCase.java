package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;

public interface ModifyStoryUseCase {

    void modify(ModifyStoryCommand command);

}