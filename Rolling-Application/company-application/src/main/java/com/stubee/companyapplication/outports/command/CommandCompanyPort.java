package com.stubee.companyapplication.outports.command;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

import java.util.List;

public interface CommandCompanyPort {

    Company register(Company company);

    void update(Company company);

    void updateAll(List<Company> companyList);

    void deleteById(CompanyId companyId);

}