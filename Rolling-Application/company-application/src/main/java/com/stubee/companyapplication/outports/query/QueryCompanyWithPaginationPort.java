package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryCompanyWithPaginationPort {

    List<Company> findByNameContaining(String name, PageRequest pageRequest);

    List<Company> findByRegistrantId(Long registrantId, PageRequest pageRequest);

    List<Company> findAll(PageRequest pageRequest);

    default PageDataResponse<List<Company>> getByNameContaining(final String name, final PageRequest pageRequest) {
        return PageDataResponse.create(findByNameContaining(name, pageRequest));
    }

    default PageDataResponse<List<Company>> getAll(final PageRequest pageRequest) {
        return PageDataResponse.create(findAll(pageRequest));
    }

}