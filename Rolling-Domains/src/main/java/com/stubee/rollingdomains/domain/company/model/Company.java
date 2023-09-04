package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.BaseId;
import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import com.stubee.rollingdomains.domain.member.exception.NotMatchedMemberException;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Company (
        CompanyId companyId,
        CompanyDetails companyDetails,
        Grades companyGrades,
        RegistrantId registrantId) {
    public static Company createWithId(final CompanyId companyId, final CompanyDetails companyDetails,
                                       final Grades companyGrades, final RegistrantId registrantId) {
        return Company.builder()
                .companyId(companyId)
                .companyDetails(companyDetails)
                .companyGrades(companyGrades)
                .registrantId(registrantId)
                .build();
    }

    public static Company create(final String name, final String address, final String description,
                                                 final String imgUrl, final RegistrantId registrantId) {
        return Company.builder()
                .companyDetails(CompanyDetails.create(name, address, description, imgUrl))
                .companyGrades(Grades.createWithTotal(0.0, 0.0, 0.0, 0.0, 0.0))
                .registrantId(registrantId)
                .build();
    }

    public Company updateGrades(final Grades companyGrades) {
        return createWithId(companyId, companyDetails, companyGrades, registrantId);
    }

    public Company updateStatus(final boolean isAccepted) {
        return createWithId(companyId, companyDetails.updateStatus(CompanyStatus.of(isAccepted)), companyGrades, registrantId);
    }

    public void isRegistrant(final BaseId memberId) {
        if(!registrantId.equals(memberId)) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}