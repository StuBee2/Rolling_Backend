package rolling.application.company.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.common.response.PageDataResponse;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.common.model.PageRequest;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryAllCompanyUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public PageDataResponse<List<CompanyResponse>> query(final PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryCompanyPort.getAll(pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}