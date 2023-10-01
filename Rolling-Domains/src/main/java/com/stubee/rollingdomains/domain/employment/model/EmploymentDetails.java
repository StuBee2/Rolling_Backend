package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public record EmploymentDetails(
        EmploymentStatus employmentStatus,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public EmploymentDetails(EmploymentStatus employmentStatus) {
        this(employmentStatus, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public EmploymentDetails {
        Objects.requireNonNull(employmentStatus, "EmploymentStatus can not be null");
    }
}