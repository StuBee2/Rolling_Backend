package com.stubee.companyapplication.outports.command;

import com.stubee.rollingdomains.domain.company.model.Company;

public interface RegisterCompanyPort {

    Company register(Company company);

}