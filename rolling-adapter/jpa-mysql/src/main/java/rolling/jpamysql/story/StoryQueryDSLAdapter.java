package rolling.jpamysql.story;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.story.interactor.query.StoryQueryByCompanyResponse;
import rolling.application.story.interactor.query.StoryQueryByMemberResponse;
import rolling.application.story.interactor.query.StoryStatusResponse;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.common.model.PageRequest;
import rolling.domain.story.model.Story;

import java.util.List;
import java.util.Optional;

import static rolling.jpamysql.story.StoryMapper.*;

@Component
@RequiredArgsConstructor
class StoryQueryDSLAdapter implements QueryStoryPort {

    private final StoryQueryDSLRepository repository;

    @Override
    public Optional<Story> findBy(final Long id) {
        return Optional.ofNullable(toDomain(repository.findById(id)));
    }

    @Override
    public Optional<StoryQueryByCompanyResponse> findInfoBy(final Long id) {
        return Optional.ofNullable(repository.findInfoById(id));
    }

    @Override
    public StoryStatusResponse getStatusBy(final Long memberId) {
        return repository.findStatusByMemberId(memberId);
    }

    @Override
    public List<StoryQueryByMemberResponse> findByMember(final Long memberId, final PageRequest pageRequest) {
        return repository.findByMemberId(memberId, pageRequest);
    }

    @Override
    public List<StoryQueryByCompanyResponse> findByCompany(final Long companyId, final PageRequest pageRequest) {
        return repository.findByCompanyId(companyId, pageRequest);
    }

}