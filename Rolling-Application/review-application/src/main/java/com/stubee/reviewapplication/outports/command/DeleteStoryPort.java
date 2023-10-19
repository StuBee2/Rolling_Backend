package com.stubee.reviewapplication.outports.command;

import com.stubee.rollingdomains.domain.story.model.StoryId;

public interface DeleteStoryPort {

    void deleteById(StoryId storyId);

}