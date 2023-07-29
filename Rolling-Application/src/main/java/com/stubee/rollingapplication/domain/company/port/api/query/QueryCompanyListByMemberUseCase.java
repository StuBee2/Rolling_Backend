package com.stubee.rollingapplication.domain.company.port.api.query;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface QueryCompanyListByMemberUseCase {

    PageDataResponse<List<Company>> getMy(PageRequest pageRequest);

    PageDataResponse<List<Company>> getByMemberId(UUID memberId, PageRequest pageRequest);

}