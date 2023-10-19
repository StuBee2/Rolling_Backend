package com.stubee.reviewapplication.outports.query;

import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.rollingdomains.domain.story.exception.StoryNotFoundException;
import com.stubee.rollingdomains.domain.story.model.Story;

import java.util.Optional;

public interface QueryStoryByIdPort {

    Optional<Story> findById(Long id);

    Optional<StoryQueryByCompanyResponse> findInfoById(Long id);

    default StoryQueryByCompanyResponse getInfoById(final Long id) {
        return findInfoById(id)
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

}