package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByGradesPort;
import com.stubee.companyapplication.usecases.query.response.CompanyResponse;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyListByGradesApi implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyByGradesPort queryCompanyByGradesPort;

    @Override
    public List<CompanyResponse> getByTotalGrade() {
        return queryCompanyByGradesPort.getByTotalGrade().stream()
                .map(CompanyResponse::of)
                .toList();
    }

    @Override
    public List<CompanyResponse> getBySalaryAndBenefits() {
        return queryCompanyByGradesPort.getBySalaryAndBenefits().stream()
                .map(CompanyResponse::of)
                .toList();
    }

    @Override
    public List<CompanyResponse> getByWorkLifeBalance() {
        return queryCompanyByGradesPort.getByWorkLifeBalance().stream()
                .map(CompanyResponse::of)
                .toList();
    }

    @Override
    public List<CompanyResponse> getByOrganizationalCulture() {
        return queryCompanyByGradesPort.getByOrganizationalCulture().stream()
                .map(CompanyResponse::of)
                .toList();
    }

    @Override
    public List<CompanyResponse> getByCareerAdvancement() {
        return queryCompanyByGradesPort.getByCareerAdvancement().stream()
                .map(CompanyResponse::of)
                .toList();
    }

}