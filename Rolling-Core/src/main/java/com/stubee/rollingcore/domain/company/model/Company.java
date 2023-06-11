package com.stubee.rollingcore.domain.company.model;

import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.dto.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.Builder;

@Builder
public record Company (
        CompanyId companyId,
        CompanyDetails companyDetails,
        Grades companyGrades,
        MemberId registrantId) {
    public static Company createExceptCompanyId(RegisterCompanyCommand command, MemberId memberId) {
        return Company.builder()
                .companyDetails(CompanyDetails.create(command.name(), command.address(), command.description(), command.imgUrl()))
                .companyGrades(Grades.createWithTotal(0.0, 0.0, 0.0, 0.0))
                .registrantId(memberId)
                .build();
    }
}