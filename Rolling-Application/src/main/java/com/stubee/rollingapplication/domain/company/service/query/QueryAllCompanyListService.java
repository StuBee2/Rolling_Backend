package com.stubee.rollingapplication.domain.company.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.query.QueryAllCompanyListUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryAllCompanyListService implements QueryAllCompanyListUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public List<Company> getList(PageRequest pageRequest) {
        return queryCompanyPort.findAll(pageRequest);
    }

}