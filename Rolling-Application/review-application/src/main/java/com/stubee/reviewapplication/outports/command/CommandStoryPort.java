package com.stubee.reviewapplication.outports.command;

import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.rollingdomains.domain.story.model.StoryId;

public interface CommandStoryPort {

    Story save(Story story);

    Story update(Story story);

    void deleteById(StoryId storyId);

}