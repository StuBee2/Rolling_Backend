package rolling.application.story.interactor.query;

import java.time.LocalDateTime;

public record StoryStatusResponse(
        Long count,
        LocalDateTime lastModifiedDate) {}