package com.stubee.companyapplication.usecases.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyListByMemberUseCase {

    PageDataResponse<List<Company>> getMy(PageRequest pageRequest);

    PageDataResponse<List<Company>> getByMemberId(UUID memberId, PageRequest pageRequest);

}