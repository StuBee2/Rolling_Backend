package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyUseCase {

    CompanyQueryResponse getInfoById(UUID companyId);

    PageDataResponse<List<Company>> getListByNameContaining(String companyName, PageRequest pageRequest);

    List<Company> getList(PageRequest pageRequest);

    PageDataResponse<List<Company>> getMy(PageRequest pageRequest);

    PageDataResponse<List<Company>> getByMemberId(UUID memberId, PageRequest pageRequest);

    List<Company> getByTotalGrade();

    List<Company> getBySalaryAndBenefits();

    List<Company> getByWorkLifeBalance();

    List<Company> getByOrganizationalCulture();

    List<Company> getByCareerAdvancement();

}