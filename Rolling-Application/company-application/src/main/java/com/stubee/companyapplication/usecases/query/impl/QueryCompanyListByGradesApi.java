package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByGradesPort;
import com.stubee.companyapplication.usecases.query.response.CompanyResponse;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyListByGradesApi implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyByGradesPort queryCompanyByGradesPort;

    @Override
    public List<CompanyResponse> get(final String gradeType) {
        return fetchByGradeType(gradeType).stream()
                .map(CompanyResponse::of)
                .toList();
    }

    private List<Company> fetchByGradeType(final String gradeType) {
        return switch (gradeType) {
            case "total" -> queryCompanyByGradesPort.getByTotalGrade();
            case "salary-benefits" -> queryCompanyByGradesPort.getBySalaryAndBenefits();
            case "balance" -> queryCompanyByGradesPort.getByWorkLifeBalance();
            case "culture" -> queryCompanyByGradesPort.getByOrganizationalCulture();
            case "career" -> queryCompanyByGradesPort.getByCareerAdvancement();
            default -> throw new IllegalArgumentException("Wrong grade type");
        };
    }

}