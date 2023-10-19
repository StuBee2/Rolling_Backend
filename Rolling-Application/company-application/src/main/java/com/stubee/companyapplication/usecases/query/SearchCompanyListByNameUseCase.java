package com.stubee.companyapplication.usecases.query;

import com.stubee.companyapplication.usecases.query.response.CompanyResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;

import java.util.List;

public interface SearchCompanyListByNameUseCase {

    PageDataResponse<List<CompanyResponse>> get(String companyName, PageRequest pageRequest);

}