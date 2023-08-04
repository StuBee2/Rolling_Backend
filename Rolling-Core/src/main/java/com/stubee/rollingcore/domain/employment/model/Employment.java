package com.stubee.rollingcore.domain.employment.model;

import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Employment(
        EmploymentId employmentId,
        MemberId employeeId,
        CompanyId employerId,
        EmploymentDetails employmentDetails) {
    public static Employment createWithEmploymentId(final EmploymentId employmentId, final MemberId employeeId,
                                                    final CompanyId employerId, final EmploymentDetails employmentDetails) {
        return Employment.builder()
                .employmentId(employmentId)
                .employeeId(employeeId)
                .employerId(employerId)
                .employmentDetails(employmentDetails)
                .build();
    }

    public static Employment createExceptEmploymentId(final MemberId employeeId, final CompanyId employerId,
                                                      final EmploymentDetails employmentDetails) {
        return Employment.builder()
                .employeeId(employeeId)
                .employerId(employerId)
                .employmentDetails(employmentDetails)
                .build();
    }
}