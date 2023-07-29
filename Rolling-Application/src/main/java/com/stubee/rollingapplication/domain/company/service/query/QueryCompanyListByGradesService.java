package com.stubee.rollingapplication.domain.company.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.query.QueryCompanyListByGradesUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.domain.company.model.Company;
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