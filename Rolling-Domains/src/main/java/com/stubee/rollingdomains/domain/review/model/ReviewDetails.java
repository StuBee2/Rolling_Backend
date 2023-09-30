package com.stubee.rollingdomains.domain.review.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public record ReviewDetails(
        String content,
        String position,
        String careerPath,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public ReviewDetails(String content, String position, String careerPath) {
        this(content, position, careerPath, null, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public ReviewDetails {
        Objects.requireNonNull(content);
        Objects.requireNonNull(position);
        Objects.requireNonNull(careerPath);
    }

}