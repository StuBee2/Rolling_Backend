package com.stubee.companyapplication.outports.query;

import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.Optional;
import java.util.UUID;

public interface QueryCompanyByIdPort {

    Optional<Company> findById(UUID id);

    Optional<CompanyQueryResponse> findInfoById(UUID id);

}