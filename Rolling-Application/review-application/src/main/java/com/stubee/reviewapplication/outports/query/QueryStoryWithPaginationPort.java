package com.stubee.reviewapplication.outports.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;

import java.util.List;

public interface QueryStoryWithPaginationPort {

    List<StoryQueryByMemberResponse> findByMemberId(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompanyId(Long companyId, PageRequest pageRequest);

}