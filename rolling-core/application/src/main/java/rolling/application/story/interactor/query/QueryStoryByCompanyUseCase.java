package rolling.application.story.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.common.response.PageDataResponse;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.common.model.PageRequest;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryStoryByCompanyUseCase {

    private final QueryStoryPort queryStoryPort;

    public PageDataResponse<List<StoryQueryByCompanyResponse>> query(Long companyId, PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryStoryPort.findByCompany(companyId, pageRequest));
    }

}