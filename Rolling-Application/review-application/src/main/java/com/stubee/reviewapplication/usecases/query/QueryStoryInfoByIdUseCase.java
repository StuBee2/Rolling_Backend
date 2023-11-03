package com.stubee.reviewapplication.usecases.query;

public interface QueryStoryInfoByIdUseCase {

    StoryQueryByCompanyResponse get(Long reviewId);

}