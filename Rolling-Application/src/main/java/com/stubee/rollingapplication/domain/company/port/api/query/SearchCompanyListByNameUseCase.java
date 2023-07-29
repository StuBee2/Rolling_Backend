package com.stubee.rollingapplication.domain.company.port.api.query;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;

public interface SearchCompanyListByNameUseCase {

    PageDataResponse<List<Company>> get(String companyName, PageRequest pageRequest);

}