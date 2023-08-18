package com.stubee.companyapplication.usecases.query;

import com.stubee.rollingdomains.domain.company.response.CompanyQueryResponse;

import java.util.UUID;

public interface QueryCompanyInfoByIdUseCase {

    CompanyQueryResponse get(UUID companyId);

}