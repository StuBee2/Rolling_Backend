package com.stubee.reviewapplication.usecases.query.response;

import java.time.LocalDateTime;

public record ReviewStatusResponse(
        Long count,
        LocalDateTime lastModifiedDate) {}