package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.domain.company.dto.response.CompanyInfoResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyUseCase {

    CompanyInfoResponse getInfoById(UUID companyId);

    List<Company> getListByNameContaining(String companyName);

    List<Company>  getList();

    List<Company> getMy();

    List<Company> getByMemberId(UUID memberId);

    List<Company> getByTotalGrade();

    List<Company> getBySalaryGrade();

    List<Company> getByWelfareGrade();

    List<Company> getByBalanceGrade();

}