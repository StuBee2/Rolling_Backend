package rolling.application.story.outport;

import rolling.application.story.interactor.query.StoryQueryByCompanyResponse;
import rolling.application.story.interactor.query.StoryQueryByMemberResponse;
import rolling.application.story.interactor.query.StoryStatusResponse;
import rolling.domain.common.model.PageRequest;
import rolling.domain.story.exception.StoryNotFoundException;
import rolling.domain.story.model.Story;

import java.util.List;
import java.util.Optional;

public interface QueryStoryPort {

    Optional<Story> findBy(Long id);

    default Story getBy(final Long id) {
        return findBy(id)
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

    Optional<StoryQueryByCompanyResponse> findInfoBy(Long id);

    default StoryQueryByCompanyResponse getInfoBy(final Long id) {
        return findInfoBy(id)
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

    StoryStatusResponse getStatusBy(Long memberId);

    List<StoryQueryByMemberResponse> findByMember(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompany(Long companyId, PageRequest pageRequest);

}