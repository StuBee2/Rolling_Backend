package com.stubee.rollingservices.domain.company.services.query;

import com.stubee.rollingservices.common.annotations.QueryService;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingusecases.domain.company.usecases.query.QueryCompanyListByGradesUseCase;
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