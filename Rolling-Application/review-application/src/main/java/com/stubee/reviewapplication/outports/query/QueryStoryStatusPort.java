package com.stubee.reviewapplication.outports.query;

import com.stubee.reviewapplication.usecases.query.StoryStatusResponse;

public interface QueryStoryStatusPort {

    StoryStatusResponse getStatusByMemberId(Long memberId);

}