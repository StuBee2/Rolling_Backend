package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.company.dto.response.CompanyInfoResponse;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@QueryService
@RequiredArgsConstructor
public class QueryCompanyService implements QueryCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public CompanyInfoResponse getInfoById(final UUID companyId) {
        CompanyQueryResponse companyQueryResponse = queryCompanyPort.findById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return toResponse(companyQueryResponse,
                queryReviewPort.findByCompanyId(companyId));
    }

    @Override
    public List<Company> getListByNameContaining(final String companyName) {
        return queryCompanyPort.findByNameContaining(companyName);
    }

    @Override
    public List<Company> getList() {
        return queryCompanyPort.findAll();
    }

    @Override
    public List<Company> getMy() {
        return queryCompanyPort.findByRegistrantId(memberSecurityPort.getCurrentMember().id());
    }

    @Override
    public List<Company> getByMemberId(final UUID memberId) {
        return queryCompanyPort.findByRegistrantId(memberId);
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

    private CompanyInfoResponse toResponse(final CompanyQueryResponse queryResponse,
                                           final List<ReviewInfoResponse> reviewList) {
        return CompanyInfoResponse.builder()
                .companyQueryResponse(queryResponse)
                .reviewInfoList(reviewList)
                .build();
    }

}