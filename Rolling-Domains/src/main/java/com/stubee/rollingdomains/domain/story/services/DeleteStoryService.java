package com.stubee.rollingdomains.domain.story.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.StoryId;

public interface DeleteStoryService {

    void delete(StoryId storyId, MemberId memberId);

}