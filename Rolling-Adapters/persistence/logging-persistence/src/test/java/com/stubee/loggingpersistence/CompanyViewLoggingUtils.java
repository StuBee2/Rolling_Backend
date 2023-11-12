package com.stubee.loggingpersistence;

import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.logging.model.CompanyViewLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public abstract class CompanyViewLoggingUtils {

    public static CompanyViewLogging create(MemberId memberId, CompanyId companyId) {
        return CompanyViewLogging.ExceptIdBuilder()
                .memberId(memberId)
                .companyId(companyId)
                .build();
    }

}