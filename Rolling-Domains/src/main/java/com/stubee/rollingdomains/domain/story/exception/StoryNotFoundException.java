package com.stubee.rollingdomains.domain.story.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class StoryNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new StoryNotFoundException();

    private StoryNotFoundException() {
        super(ErrorCode.STORY_NOT_FOUND);
    }

}