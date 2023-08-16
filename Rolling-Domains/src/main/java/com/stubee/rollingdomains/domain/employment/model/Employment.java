package com.stubee.rollingdomains.domain.employment.model;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Employment(
        EmploymentId employmentId,
        EmployeeId employeeId,
        EmployerId employerId,
        EmploymentDetails employmentDetails) {
    public static Employment createWithEmploymentId(final EmploymentId employmentId, final EmployeeId employeeId,
                                                    final EmployerId employerId, final EmploymentDetails employmentDetails) {
        return Employment.builder()
                .employmentId(employmentId)
                .employeeId(employeeId)
                .employerId(employerId)
                .employmentDetails(employmentDetails)
                .build();
    }

    public static Employment createExceptEmploymentId(final EmployeeId employeeId, final EmployerId employerId,
                                                      final EmploymentDetails employmentDetails) {
        return Employment.builder()
                .employeeId(employeeId)
                .employerId(employerId)
                .employmentDetails(employmentDetails)
                .build();
    }
}