package rolling.domain.company;

import rolling.domain.company.service.CompanyService;

public class CompanyServiceForSuccess implements CompanyService {

    @Override
    public boolean isNameDuplicate(String name) {
        return false;
    }

}
