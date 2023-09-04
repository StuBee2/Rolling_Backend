package com.stubee.companyapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByGradesPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyListByGradesService implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyByGradesPort queryCompanyByGradesPort;

    @Override
    public List<Company> getByTotalGrade() {
        return queryCompanyByGradesPort.findByTotalGrade();
    }

    @Override
    public List<Company> getBySalaryAndBenefits() {
        return queryCompanyByGradesPort.findBySalaryAndBenefits();
    }

    @Override
    public List<Company> getByWorkLifeBalance() {
        return queryCompanyByGradesPort.findByWorkLifeBalance();
    }

    @Override
    public List<Company> getByOrganizationalCulture() {
        return queryCompanyByGradesPort.findByOrganizationalCulture();
    }

    @Override
    public List<Company> getByCareerAdvancement() {
        return queryCompanyByGradesPort.findByCareerAdvancement();
    }

}