package com.stubee.companyapplication.outports.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyWithPaginationPort {

    List<Company> findByNameContaining(String name, PageRequest pageRequest);

    List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest);

    List<Company> findAll(PageRequest pageRequest);

}