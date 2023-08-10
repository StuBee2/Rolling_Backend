package com.stubee.rollingservices.domain.company.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingusecases.domain.company.usecases.query.SearchCompanyListByNameUseCase;
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