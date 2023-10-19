package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByGradesPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyListByGradesApi implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyByGradesPort queryCompanyByGradesPort;

    @Override
    public List<Company> getByTotalGrade() {
        return queryCompanyByGradesPort.getByTotalGrade();
    }

    @Override
    public List<Company> getBySalaryAndBenefits() {
        return queryCompanyByGradesPort.getBySalaryAndBenefits();
    }

    @Override
    public List<Company> getByWorkLifeBalance() {
        return queryCompanyByGradesPort.getByWorkLifeBalance();
    }

    @Override
    public List<Company> getByOrganizationalCulture() {
        return queryCompanyByGradesPort.getByOrganizationalCulture();
    }

    @Override
    public List<Company> getByCareerAdvancement() {
        return queryCompanyByGradesPort.getByCareerAdvancement();
    }

}