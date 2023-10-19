package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.reviewapplication.outports.command.CommandStoryPort;
import com.stubee.reviewapplication.outports.query.QueryStoryByIdPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.exception.StoryNotFoundException;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.rollingdomains.domain.story.model.StoryId;
import com.stubee.rollingdomains.domain.story.services.DeleteStoryService;
import com.stubee.rollingdomains.domain.story.services.WriteStoryService;
import com.stubee.rollingdomains.domain.story.services.commands.DeleteStoryCommand;
import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class StoryDomainService implements WriteStoryService, DeleteStoryService {

    private final CommandStoryPort commandStoryPort;
    private final QueryStoryByIdPort queryStoryByIdPort;

    @Override
    public Story write(final RegisterStoryCommand command, final MemberId memberId) {
        return commandStoryPort.register(command.toDomain(memberId));
    }

    @Override
    public void delete(final DeleteStoryCommand command, final MemberId memberId) {
        final Story story = this.getById(command.storyId());

        story.isAuthor(memberId);

        commandStoryPort.deleteById(command.storyId());
    }

    private Story getById(final StoryId storyId) {
        return queryStoryByIdPort.findById(storyId.getId())
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

}