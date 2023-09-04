package com.stubee.companyapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyInfoByIdUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.companyapplication.services.query.response.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyInfoByIdService implements QueryCompanyInfoByIdUseCase {

    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public CompanyQueryResponse get(UUID companyId) {
        return queryCompanyByIdPort.findInfoById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

}