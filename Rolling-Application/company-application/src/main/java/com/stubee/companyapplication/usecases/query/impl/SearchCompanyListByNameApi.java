package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyWithPaginationPort;
import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.companyapplication.usecases.query.SearchCompanyListByNameUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class SearchCompanyListByNameApi implements SearchCompanyListByNameUseCase {

    private final QueryCompanyWithPaginationPort queryCompanyWithPaginationPort;

    @Override
    public PageDataResponse<List<CompanyResponse>> get(String companyName, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyWithPaginationPort.findByNameContaining(companyName, pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}