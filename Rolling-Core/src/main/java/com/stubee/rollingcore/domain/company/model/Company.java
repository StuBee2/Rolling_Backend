package com.stubee.rollingcore.domain.company.model;

import com.stubee.rollingcore.common.exception.NotMatchedMemberException;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Company (
        CompanyId companyId,
        CompanyDetails companyDetails,
        Grades companyGrades,
        MemberId registrantId) {
    public static Company createWithId(final CompanyId companyId, final CompanyDetails companyDetails,
                                       final Grades companyGrades, final MemberId registrantId) {
        return Company.builder()
                .companyId(companyId)
                .companyDetails(companyDetails)
                .companyGrades(companyGrades)
                .registrantId(registrantId)
                .build();
    }

    public static Company create(final String name, final String address, final String description,
                                                 final String imgUrl, MemberId memberId) {
        return Company.builder()
                .companyDetails(CompanyDetails.create(name, address, description, imgUrl))
                .companyGrades(Grades.createWithTotal(0.0, 0.0, 0.0, 0.0, 0.0))
                .registrantId(memberId)
                .build();
    }

    public Company updateGrades(final Grades companyGrades) {
        return createWithId(companyId, companyDetails, companyGrades, registrantId);
    }

    public void isRightRegistrant(final MemberId memberId) {
        if(registrantId.equals(memberId)) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}