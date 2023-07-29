package com.stubee.rollingapplication.domain.company.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.query.QueryCompanyInfoByIdUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.response.CompanyQueryResponse;
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