package com.stubee.companyapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyPort;
import com.stubee.companyapplication.usecases.query.QueryAllCompanyListUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryAllCompanyListService implements QueryAllCompanyListUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public PageDataResponse<List<Company>> get(PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findAll(pageRequest));
    }

}