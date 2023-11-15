package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyWithPaginationPort;
import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.companyapplication.usecases.query.SearchCompanyListByNameUseCase;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class SearchCompanyListByNameApi implements SearchCompanyListByNameUseCase {

    private final QueryCompanyWithPaginationPort queryCompanyWithPaginationPort;

    @Override
    public PageDataResponse<List<CompanyResponse>> get(String companyName, PageRequest pageRequest) {
        pageRequest.validate();

        return PageDataResponse.of(queryCompanyWithPaginationPort.findByNameContaining(companyName, pageRequest).stream()
                .map(CompanyResponse::of)
                .toList());
    }

}