package com.stubee.companyapplication.usecases.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface SearchCompanyListByNameUseCase {

    PageDataResponse<List<Company>> get(String companyName, PageRequest pageRequest);

}