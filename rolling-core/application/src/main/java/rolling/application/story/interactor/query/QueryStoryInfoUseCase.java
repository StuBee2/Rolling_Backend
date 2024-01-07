package rolling.application.story.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.story.outport.QueryStoryPort;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryStoryInfoUseCase {

    private final QueryStoryPort queryStoryPort;

    public StoryQueryByCompanyResponse query(final Long reviewId) {
        return queryStoryPort.getInfoBy(reviewId);
    }

}