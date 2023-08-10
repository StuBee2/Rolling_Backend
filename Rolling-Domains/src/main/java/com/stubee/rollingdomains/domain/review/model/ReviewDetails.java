package com.stubee.rollingdomains.domain.review.model;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewDetails(
        String content,
        String position,
        String careerPath,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static ReviewDetails create(final String content, final String position, final String careerPath) {
        return ReviewDetails.builder()
                .content(content)
                .position(position)
                .careerPath(careerPath)
                .build();
    }

    public static ReviewDetails createWithDate(final String content, final String position, final String careerPath,
                                               final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        return ReviewDetails.builder()
                .content(content)
                .position(position)
                .careerPath(careerPath)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

}