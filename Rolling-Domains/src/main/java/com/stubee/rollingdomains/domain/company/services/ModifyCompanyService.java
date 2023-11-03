package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

public interface ModifyCompanyService {

    void modify(Long id, CompanyDetails companyDetails);

    void modify(CompanyId companyId, CompanyStatus status);

}