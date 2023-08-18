package com.stubee.companyapplication.outports;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

public interface CommandCompanyPort {

    Company create(Company company);

    void update(Company company);

    void deleteById(CompanyId companyId);

}