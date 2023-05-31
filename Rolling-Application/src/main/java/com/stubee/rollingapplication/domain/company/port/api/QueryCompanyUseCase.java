package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.common.dto.PageDto;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyUseCase {

    CompanyQueryResponse getInfoById(UUID companyId);

    List<Company> getListByNameContaining(String companyName, PageDto pageDto);

    List<Company>  getList();

    List<Company> getMy(PageDto pageDto);

    List<Company> getByMemberId(UUID memberId, PageDto pageDto);

    List<Company> getByTotalGrade();

    List<Company> getBySalaryGrade();

    List<Company> getByWelfareGrade();

    List<Company> getByBalanceGrade();

}