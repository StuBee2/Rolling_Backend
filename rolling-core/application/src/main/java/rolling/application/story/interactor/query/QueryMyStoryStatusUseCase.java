package rolling.application.story.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.story.outport.QueryStoryPort;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryMyStoryStatusUseCase {

    private final MemberSessionPort memberSessionPort;
    private final QueryStoryPort queryStoryPort;

    public StoryStatusResponse query() {
        final Long memberId = memberSessionPort.currentId().getId();

        return queryStoryPort.getStatusBy(memberId);
    }

}