package com.stubee.rollingusecases.domain.company.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryAllCompanyListUseCase {

    List<Company> get(PageRequest pageRequest);

}