package com.stubee.companyapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.QueryCompanyPort;
import com.stubee.companyapplication.usecases.query.SearchCompanyListByNameUseCase;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class SearchCompanyListByNameService implements SearchCompanyListByNameUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public PageDataResponse<List<Company>> get(String companyName, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findByNameContaining(companyName, pageRequest));
    }

}