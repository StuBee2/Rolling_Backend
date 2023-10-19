package com.stubee.rollingdomains.domain.logging.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

import java.time.LocalDateTime;

public record CompanyViewLogging(
        Long id,
        MemberId memberId,
        CompanyId companyId,
        Boolean isAnonymous,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public CompanyViewLogging(MemberId memberId, CompanyId companyId) {
        this(null, memberId, companyId, false, null);
    }

    @Builder(builderClassName = "AnonymousBuilder", builderMethodName = "AnonymousBuilder")
    public CompanyViewLogging(CompanyId companyId) {
        this(null, null, companyId, true, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public CompanyViewLogging {
        Assert.notNull(companyId, "CompanyId must not be null");
        Assert.notNull(isAnonymous, "IsAnonymous must not be null");
    }
}