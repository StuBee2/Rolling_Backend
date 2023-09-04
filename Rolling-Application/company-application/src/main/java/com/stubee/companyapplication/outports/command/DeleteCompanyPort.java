package com.stubee.companyapplication.outports.command;

import com.stubee.rollingdomains.domain.company.model.CompanyId;

public interface DeleteCompanyPort {

    void deleteById(CompanyId companyId);

}