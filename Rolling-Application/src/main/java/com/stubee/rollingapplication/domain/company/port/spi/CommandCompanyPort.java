package com.stubee.rollingapplication.domain.company.port.spi;

import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;

public interface CommandCompanyPort {

    Company save(Company company);

    void update(Company company);

    void deleteById(CompanyId companyId);

}