package com.stubee.reviewapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.reviewapplication.outports.query.QueryStoryByIdPort;
import com.stubee.reviewapplication.usecases.query.QueryStoryInfoByIdUseCase;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryStoryInfoByIdApi implements QueryStoryInfoByIdUseCase {

    private final QueryStoryByIdPort queryReviewByIdPort;

    @Override
    public StoryQueryByCompanyResponse get(final Long reviewId) {
        return queryReviewByIdPort.getInfoById(reviewId);
    }

}