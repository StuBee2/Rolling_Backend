package com.stubee.rollingapplication.domain.company.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.query.SearchCompanyListByNameUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.company.model.Company;
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