package com.stubee.rollingapplication.domain.company.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyService implements QueryCompanyUseCase {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public CompanyQueryResponse getInfoById(final UUID companyId) {
        return queryCompanyPort.findById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    @Override
    public PageDataResponse<List<Company>> getListByNameContaining(final String companyName, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findByNameContaining(companyName, pageRequest));
    }

    @Override
    public List<Company> getList(PageRequest pageRequest) {
        return queryCompanyPort.findAll(pageRequest);
    }

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