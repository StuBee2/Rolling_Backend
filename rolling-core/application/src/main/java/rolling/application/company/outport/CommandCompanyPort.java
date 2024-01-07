package rolling.application.company.outport;

import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyId;

import java.util.List;

public interface CommandCompanyPort {

    Company save(Company company);

    void updateAll(List<Company> companyList);

    void deleteById(CompanyId companyId);

}