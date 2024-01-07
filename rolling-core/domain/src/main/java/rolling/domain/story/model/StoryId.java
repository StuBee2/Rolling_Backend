package rolling.domain.story.model;

import rolling.domain.common.model.BaseId;

public class StoryId extends BaseId {

    private StoryId(Long id) {
        super(id);
    }

    public static StoryId of(final Long id) {
        return new StoryId(id);
    }

}