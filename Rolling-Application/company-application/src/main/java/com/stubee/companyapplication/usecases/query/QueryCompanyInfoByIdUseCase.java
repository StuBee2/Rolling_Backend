package com.stubee.companyapplication.usecases.query;

import com.stubee.companyapplication.services.query.response.CompanyQueryResponse;

import java.util.UUID;

public interface QueryCompanyInfoByIdUseCase {

    CompanyQueryResponse get(UUID companyId);

}