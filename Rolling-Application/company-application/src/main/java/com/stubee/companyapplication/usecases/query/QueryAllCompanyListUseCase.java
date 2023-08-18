package com.stubee.companyapplication.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryAllCompanyListUseCase {

    PageDataResponse<List<Company>> get(PageRequest pageRequest);

}