package com.stubee.companyapplication.outports;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

import java.util.List;

public interface CommandCompanyPort {

    Company create(Company company);

    void update(Company company);

    void updateAll(List<Company> companyList);

    void deleteById(CompanyId companyId);

}