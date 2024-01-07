package rolling.application.story.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.common.response.PageDataResponse;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.common.model.PageRequest;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryStoryByMemberUseCase {

    private final MemberSessionPort memberSessionPort;
    private final QueryStoryPort queryStoryPort;

    public PageDataResponse<List<StoryQueryByMemberResponse>> query(final PageRequest pageRequest) {
        final Long memberId = memberSessionPort.currentId().getId();

        return query(memberId, pageRequest);
    }

    public PageDataResponse<List<StoryQueryByMemberResponse>> query(final Long memberId, final PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryStoryPort.findByMember(memberId, pageRequest));
    }

}