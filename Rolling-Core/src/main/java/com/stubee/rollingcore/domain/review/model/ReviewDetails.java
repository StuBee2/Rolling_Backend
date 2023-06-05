package com.stubee.rollingcore.domain.review.model;

import lombok.Builder;

@Builder
public record ReviewDetails(
        String content,
        String position,
        String careerPath) {}