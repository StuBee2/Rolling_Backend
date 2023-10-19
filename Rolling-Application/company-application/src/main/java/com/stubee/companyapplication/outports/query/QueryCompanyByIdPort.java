package com.stubee.companyapplication.outports.query;

import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.Optional;

public interface QueryCompanyByIdPort {

    Optional<Company> findById(Long id);

    Optional<CompanyQueryResponse> findInfoById(Long id);

    default CompanyQueryResponse getInfoById(final Long id) {
        return findInfoById(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

}