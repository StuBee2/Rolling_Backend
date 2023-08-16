package com.stubee.rollingservices.domain.company.services.query;

import com.stubee.rollingservices.common.annotations.QueryService;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.response.CompanyQueryResponse;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingusecases.domain.company.usecases.query.QueryCompanyInfoByIdUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyInfoByIdService implements QueryCompanyInfoByIdUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public CompanyQueryResponse get(UUID companyId) {
        return queryCompanyPort.findInfoById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

}