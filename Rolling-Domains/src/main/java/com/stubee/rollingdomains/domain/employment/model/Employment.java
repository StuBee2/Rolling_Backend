package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

public record Employment(
        EmploymentId employmentId,
        EmployeeId employeeId,
        EmployerId employerId,
        EmploymentDetails employmentDetails) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Employment(EmployeeId employeeId, EmployerId employerId, EmploymentDetails employmentDetails) {
        this(null, employeeId, employerId, employmentDetails);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Employment {
        Assert.notNull(employeeId, "EmployeeId must not be null");
        Assert.notNull(employerId, "EmployerId must not be null");
        Assert.notNull(employmentDetails, "EmploymentDetails must not be null");
    }
}