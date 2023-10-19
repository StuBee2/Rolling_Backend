package com.stubee.reviewapplication.outports.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;

import java.util.List;

public interface QueryStoryWithPaginationPort {

    List<StoryQueryByMemberResponse> findByMemberId(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompanyId(Long companyId, PageRequest pageRequest);

    default PageDataResponse<List<StoryQueryByMemberResponse>> getByMemberId(final Long memberId, final PageRequest pageRequest) {
        return PageDataResponse.create(findByMemberId(memberId, pageRequest));
    }

    default PageDataResponse<List<StoryQueryByCompanyResponse>> getByCompanyId(final Long companyId, final PageRequest pageRequest) {
        return PageDataResponse.create(findByCompanyId(companyId, pageRequest));
    }

}