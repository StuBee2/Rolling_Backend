package rolling.application.story.outport;

import rolling.domain.story.model.Story;
import rolling.domain.story.model.StoryId;

public interface CommandStoryPort {

    Story save(Story story);

    void deleteById(StoryId storyId);

}