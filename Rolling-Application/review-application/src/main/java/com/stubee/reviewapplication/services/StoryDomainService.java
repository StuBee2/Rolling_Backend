package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.reviewapplication.outports.command.CommandStoryPort;
import com.stubee.reviewapplication.outports.query.QueryStoryByIdPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.exception.StoryNotFoundException;
import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.rollingdomains.domain.story.services.DeleteStoryService;
import com.stubee.rollingdomains.domain.story.services.ModifyStoryService;
import com.stubee.rollingdomains.domain.story.services.RegisterStoryService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class StoryDomainService implements RegisterStoryService, DeleteStoryService, ModifyStoryService {

    private final CommandStoryPort commandStoryPort;
    private final QueryStoryByIdPort queryStoryByIdPort;

    @Override
    public Story register(final Story story) {
        return commandStoryPort.save(story);
    }

    @Override
    public void delete(final StoryId storyId, final MemberId memberId) {
        final Story story = this.getById(storyId);

        story.isAuthor(memberId);

        commandStoryPort.deleteById(storyId);
    }

    @Override
    public void modify(final StoryId id, final EmploymentDetails employmentDetails, final CorporationDetails corporationDetails,
                       final ReviewGrades reviewGrades, final MemberId memberId) {
        final Story story = this.getById(id);

        story.isAuthor(memberId);

        commandStoryPort.update(story.update(employmentDetails, corporationDetails, reviewGrades));
    }

    private Story getById(final StoryId storyId) {
        return queryStoryByIdPort.findById(storyId.getId())
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

}