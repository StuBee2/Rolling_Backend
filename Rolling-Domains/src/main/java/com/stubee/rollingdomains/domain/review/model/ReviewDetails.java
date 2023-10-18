package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

import java.time.LocalDateTime;

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
        Assert.notNull(content, "Content must not be null");
        Assert.notNull(position, "Position must not be null");
        Assert.notNull(careerPath, "CareerPath must not be null");
    }

}