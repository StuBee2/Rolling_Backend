package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyInfoByIdUseCase;
import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyInfoByIdApi implements QueryCompanyInfoByIdUseCase {

    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public CompanyQueryResponse get(final UUID companyId) {
        return queryCompanyByIdPort.getInfoById(companyId);
    }

}