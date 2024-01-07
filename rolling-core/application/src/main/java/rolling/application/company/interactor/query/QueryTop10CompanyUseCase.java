package rolling.application.company.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.QueryCompanyPort;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryTop10CompanyUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public List<CompanyResponse> query(final String gradeType) {
        return queryCompanyPort.getOrderBy(gradeType).stream()
                .map(CompanyResponse::of)
                .toList();
    }

}