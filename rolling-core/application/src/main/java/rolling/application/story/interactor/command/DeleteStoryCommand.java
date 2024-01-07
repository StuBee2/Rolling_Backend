package rolling.application.story.interactor.command;

import rolling.domain.story.model.StoryId;

public record DeleteStoryCommand(StoryId storyId) {}