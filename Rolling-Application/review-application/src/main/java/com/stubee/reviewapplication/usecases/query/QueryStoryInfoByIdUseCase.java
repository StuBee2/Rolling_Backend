package com.stubee.reviewapplication.usecases.query;

import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;

public interface QueryStoryInfoByIdUseCase {

    StoryQueryByCompanyResponse get(Long reviewId);

}