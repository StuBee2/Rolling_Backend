package com.stubee.reviewapplication.usecases.query;

import java.time.LocalDateTime;

public record StoryStatusResponse(
        Long count,
        LocalDateTime lastModifiedDate) {}