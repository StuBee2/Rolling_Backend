package rolling.application.company.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.common.response.PageDataResponse;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.common.model.PageRequest;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class SearchCompanyByNameUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public PageDataResponse<List<CompanyResponse>> query(final String name, final PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryCompanyPort.searchByName(name, pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}