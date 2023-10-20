package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

public record Employment(
        EmploymentId employmentId,
        EmploymentDetails employmentDetails) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Employment(EmploymentDetails employmentDetails) {
        this(null, employmentDetails);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Employment {
        Assert.notNull(employmentDetails, "EmploymentDetails must not be null");
    }
}