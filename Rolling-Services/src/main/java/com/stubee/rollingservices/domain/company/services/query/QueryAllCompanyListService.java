package com.stubee.rollingservices.domain.company.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingusecases.domain.company.usecases.query.QueryAllCompanyListUseCase;
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