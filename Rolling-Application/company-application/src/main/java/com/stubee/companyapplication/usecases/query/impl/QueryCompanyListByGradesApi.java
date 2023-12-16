package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByGradesPort;
import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
class QueryCompanyListByGradesApi implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyByGradesPort queryCompanyByGradesPort;

    @Override
    public List<CompanyResponse> get(final String gradeType) {
        return fetchByGradeType(gradeType).stream()
                .map(CompanyResponse::of)
                .toList();
    }

    private List<Company> fetchByGradeType(final String gradeType) {
        return queryCompanyByGradesPort.getOrderBy(gradeType);
    }

}