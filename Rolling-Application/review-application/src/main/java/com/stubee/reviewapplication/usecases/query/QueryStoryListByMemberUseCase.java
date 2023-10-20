package com.stubee.reviewapplication.usecases.query;

import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;

import java.util.List;

public interface QueryStoryListByMemberUseCase {

    PageDataResponse<List<StoryQueryByMemberResponse>> get(Long memberId, PageRequest pageRequest);

}