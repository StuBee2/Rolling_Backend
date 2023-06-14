package com.stubee.rollingcore.domain.company.model;

import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.dto.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
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

    public static Company create(CompanyId companyId, CompanyDetails companyDetails, Grades companyGrades, MemberId registrantId) {
        return Company.builder()
                .companyId(companyId)
                .companyDetails(companyDetails)
                .companyGrades(companyGrades)
                .registrantId(registrantId)
                .build();
    }

    public Company updateGrades(final double totalAvg, final double balanceAvg, final double salaryAvg, final double welfareAvg) {
        return create(companyId, companyDetails, updateAverages(totalAvg, balanceAvg, salaryAvg, welfareAvg), registrantId);
    }

    private Grades updateAverages(final double totalAvg, final double balanceAvg, final double salaryAvg, final double welfareAvg) {
        return Grades.createWithTotal(totalAvg, balanceAvg, salaryAvg, welfareAvg);
    }
}