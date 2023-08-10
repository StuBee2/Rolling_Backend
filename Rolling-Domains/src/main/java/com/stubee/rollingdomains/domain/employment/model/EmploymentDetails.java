package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record EmploymentDetails(
        EmploymentStatus employmentStatus,
        LocalDateTime createdAt) {
    public static EmploymentDetails create(final EmploymentStatus employmentStatus) {
        return EmploymentDetails.builder()
                .employmentStatus(employmentStatus)
                .build();
    }

    public static EmploymentDetails createWithDate(final EmploymentStatus employmentStatus, final LocalDateTime createdAt) {
        return EmploymentDetails.builder()
                .employmentStatus(employmentStatus)
                .createdAt(createdAt)
                .build();
    }
}