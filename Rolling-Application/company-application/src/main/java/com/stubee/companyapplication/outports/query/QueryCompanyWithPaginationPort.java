package com.stubee.companyapplication.outports.query;

import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface QueryCompanyWithPaginationPort {

    List<Company> findByNameContaining(String name, PageRequest pageRequest);

    List<Company> findByRegistrantId(Long registrantId, PageRequest pageRequest);

    List<Company> findAll(PageRequest pageRequest);

}