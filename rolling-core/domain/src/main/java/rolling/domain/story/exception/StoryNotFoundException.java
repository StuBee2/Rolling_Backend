package rolling.domain.story.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class StoryNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new StoryNotFoundException();

    private StoryNotFoundException() {
        super(ErrorCode.STORY_NOT_FOUND);
    }

}