package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;
import com.stubee.rollingdomains.domain.story.model.Story;

public interface RegisterStoryUseCase {

    Story register(RegisterStoryCommand command);

}