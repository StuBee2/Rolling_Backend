package com.stubee.rollingdomains.domain.employment.model;

import lombok.Builder;

import java.util.Objects;

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
        Objects.requireNonNull(employeeId, "EmployeeId can not be null");
        Objects.requireNonNull(employerId, "EmployerId can not be null");
        Objects.requireNonNull(employmentDetails, "EmploymentDetails can not be null");
    }
}