package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyWithPaginationPort;
import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.companyapplication.usecases.query.QueryAllCompanyListUseCase;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
class QueryAllCompanyListApi implements QueryAllCompanyListUseCase {

    private final QueryCompanyWithPaginationPort queryCompanyWithPaginationPort;

    @Override
    public PageDataResponse<List<CompanyResponse>> get(PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryCompanyWithPaginationPort.findAll(pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}