package com.stubee.rollingdomains.domain.story.services.commands;

import com.stubee.rollingdomains.domain.story.model.StoryId;

public record DeleteStoryCommand(
        StoryId storyId) {
    public static DeleteStoryCommand toCommand(final Long companyId) {
        return new DeleteStoryCommand(StoryId.of(companyId));
    }
}