package rolling.domain.company;

import rolling.domain.company.service.CompanyService;

public class CompanyServiceForFailure implements CompanyService {

    @Override
    public boolean isNameDuplicate(final String name) {
        return true;
    }

}