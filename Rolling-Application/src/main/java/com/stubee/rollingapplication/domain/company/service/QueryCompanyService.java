package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@QueryService
@RequiredArgsConstructor
public class QueryCompanyService implements QueryCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
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
    public List<Company> getList() {
        return queryCompanyPort.findAll();
    }

    @Override
    public PageDataResponse<List<Company>> getMy(PageRequest pageRequest) {
        return getByMemberId(memberSecurityPort.getCurrentMember().memberId().id(), pageRequest);
    }

    @Override
    public PageDataResponse<List<Company>> getByMemberId(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findByRegistrantId(memberId, pageRequest));
    }

    @Override
    public List<Company> getByTotalGrade() {
        return queryCompanyPort.findByTotalGrade();
    }

    @Override
    public List<Company> getBySalaryGrade() {
        return queryCompanyPort.findBySalaryGrade();
    }

    @Override
    public List<Company> getByWelfareGrade() {
        return queryCompanyPort.findByWelfareGrade();
    }

    @Override
    public List<Company> getByBalanceGrade() {
        return queryCompanyPort.findByBalanceGrade();
    }

}