package com.stubee.rollingapplication.domain.company.port.api.query;

import com.stubee.rollingcore.domain.company.response.CompanyQueryResponse;

import java.util.UUID;

public interface QueryCompanyInfoByIdUseCase {

    CompanyQueryResponse get(UUID companyId);

}