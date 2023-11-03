package com.stubee.companyapplication.usecases.query;

public interface QueryCompanyInfoByIdUseCase {

    CompanyQueryResponse get(Long companyId);

}