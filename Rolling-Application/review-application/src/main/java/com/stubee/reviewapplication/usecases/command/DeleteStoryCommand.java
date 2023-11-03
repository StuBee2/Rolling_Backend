package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.story.model.StoryId;

public record DeleteStoryCommand(
        StoryId storyId) {
    public static DeleteStoryCommand toCommand(final Long companyId) {
        return new DeleteStoryCommand(StoryId.of(companyId));
    }
}