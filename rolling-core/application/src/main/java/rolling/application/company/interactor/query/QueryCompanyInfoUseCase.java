package rolling.application.company.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.company.events.CompanyViewedEvent;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryCompanyInfoUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyQueryResponse query(final Long companyId) {
        final CompanyQueryResponse response = queryCompanyPort.getInfoBy(companyId);

        applicationEventPublisher.publishEvent(CompanyViewedEvent.of(companyId));

        return response;
    }

}