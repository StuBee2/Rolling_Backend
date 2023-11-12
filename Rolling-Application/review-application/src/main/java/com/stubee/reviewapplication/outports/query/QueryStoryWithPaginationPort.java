package com.stubee.reviewapplication.outports.query;

import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;

import java.util.List;

public interface QueryStoryWithPaginationPort {

    List<StoryQueryByMemberResponse> findByMemberId(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompanyId(Long companyId, PageRequest pageRequest);

}