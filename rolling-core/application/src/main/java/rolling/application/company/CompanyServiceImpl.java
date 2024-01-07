package rolling.application.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.company.service.CompanyService;

@Service
@RequiredArgsConstructor
class CompanyServiceImpl implements CompanyService {

    private final QueryCompanyPort queryCompanyPort;

    public boolean isNameDuplicate(final String name) {
        return queryCompanyPort.existsBy(name);
    }

}