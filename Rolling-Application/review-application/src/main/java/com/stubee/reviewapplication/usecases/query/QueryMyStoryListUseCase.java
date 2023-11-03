package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;

import java.util.List;

public interface QueryMyStoryListUseCase {

    PageDataResponse<List<StoryQueryByMemberResponse>> get(PageRequest pageRequest);

}