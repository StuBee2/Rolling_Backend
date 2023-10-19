package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class StoryId extends BaseId {

    private StoryId(Long id) {
        super(id);
    }

    public static StoryId of(final Long id) {
        return new StoryId(id);
    }

}