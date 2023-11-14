package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.applicationcommons.model.response.PageDataResponse;

import java.util.List;

public interface QueryMyStoryListUseCase {

    PageDataResponse<List<StoryQueryByMemberResponse>> get(PageRequest pageRequest);

}