package com.stubee.reviewapplication.usecases.query.response;

import java.time.LocalDateTime;

public record StoryStatusResponse(
        Long count,
        LocalDateTime lastModifiedDate) {}