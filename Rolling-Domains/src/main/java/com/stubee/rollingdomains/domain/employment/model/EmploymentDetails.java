package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public record EmploymentDetails(
        EmployeeId employeeId,
        EmployerId employerId,
        EmploymentStatus employmentStatus,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public EmploymentDetails(EmployeeId employeeId, EmployerId employerId, EmploymentStatus employmentStatus) {
        this(employeeId, employerId, employmentStatus, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public EmploymentDetails {
        Assert.notNull(employeeId, "EmployeeId must not be null");
        Assert.notNull(employerId, "EmployerId must not be null");
        Assert.notNull(employmentStatus, "EmploymentStatus must not be null");
    }
}