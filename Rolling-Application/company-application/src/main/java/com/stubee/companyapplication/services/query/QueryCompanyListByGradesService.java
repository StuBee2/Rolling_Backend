package com.stubee.companyapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyListByGradesService implements QueryCompanyListByGradesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public List<Company> getByTotalGrade() {
        return queryCompanyPort.findByTotalGrade();
    }

    @Override
    public List<Company> getBySalaryAndBenefits() {
        return queryCompanyPort.findBySalaryAndBenefits();
    }

    @Override
    public List<Company> getByWorkLifeBalance() {
        return queryCompanyPort.findByWorkLifeBalance();
    }

    @Override
    public List<Company> getByOrganizationalCulture() {
        return queryCompanyPort.findByOrganizationalCulture();
    }

    @Override
    public List<Company> getByCareerAdvancement() {
        return queryCompanyPort.findByCareerAdvancement();
    }

}