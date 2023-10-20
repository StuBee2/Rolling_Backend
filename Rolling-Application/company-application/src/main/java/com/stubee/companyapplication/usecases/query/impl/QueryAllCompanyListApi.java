package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyWithPaginationPort;
import com.stubee.companyapplication.usecases.query.response.CompanyResponse;
import com.stubee.companyapplication.usecases.query.QueryAllCompanyListUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryAllCompanyListApi implements QueryAllCompanyListUseCase {

    private final QueryCompanyWithPaginationPort queryCompanyWithPaginationPort;

    @Override
    public PageDataResponse<List<CompanyResponse>> get(PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyWithPaginationPort.findAll(pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}