package com.stubee.companyapplication.outports.command;

import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface UpdateCompanyPort {

    void updateAll(List<Company> companyList);

}