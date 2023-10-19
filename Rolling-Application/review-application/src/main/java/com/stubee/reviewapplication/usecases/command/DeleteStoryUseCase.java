package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.story.services.commands.DeleteStoryCommand;

public interface DeleteStoryUseCase {

    void delete(DeleteStoryCommand command);

}