package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public record EmploymentDetails(
        EmploymentStatus employmentStatus,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public EmploymentDetails(EmploymentStatus employmentStatus) {
        this(employmentStatus, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public EmploymentDetails {
        Assert.notNull(employmentStatus, "EmploymentStatus must not be null");
    }
}