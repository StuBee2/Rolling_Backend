package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyWithPaginationPort;
import com.stubee.companyapplication.usecases.query.SearchCompanyListByNameUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class SearchCompanyListByNameApi implements SearchCompanyListByNameUseCase {

    private final QueryCompanyWithPaginationPort queryCompanyWithPaginationPort;

    @Override
    public PageDataResponse<List<Company>> get(String companyName, PageRequest pageRequest) {
        return queryCompanyWithPaginationPort.getByNameContaining(companyName, pageRequest);
    }

}