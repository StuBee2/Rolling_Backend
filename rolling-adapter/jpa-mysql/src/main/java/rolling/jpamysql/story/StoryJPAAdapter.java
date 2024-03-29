package rolling.jpamysql.story;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.story.outport.CommandStoryPort;
import rolling.domain.story.model.Story;
import rolling.domain.story.model.StoryId;

import static rolling.jpamysql.story.StoryMapper.*;

@Component
@RequiredArgsConstructor
class StoryJPAAdapter implements CommandStoryPort {

    private final StoryJpaRepository repository;

    @Override
    public Story save(final Story story) {
        if(story.id() == null) {
            return toDomain(repository.save(toEntity(story)));
        }

        return toDomain(repository.save(toEntityWithId(story)));
    }

    @Override
    public void deleteById(final StoryId storyId) {
        repository.deleteById(storyId.getId());
    }

}