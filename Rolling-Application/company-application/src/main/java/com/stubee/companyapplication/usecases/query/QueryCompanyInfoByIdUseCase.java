package com.stubee.companyapplication.usecases.query;

import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;

public interface QueryCompanyInfoByIdUseCase {

    CompanyQueryResponse get(Long companyId);

}