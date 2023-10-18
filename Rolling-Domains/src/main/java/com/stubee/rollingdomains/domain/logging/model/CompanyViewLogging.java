package com.stubee.rollingdomains.domain.logging.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyViewLogging(
        Long id,
        UUID memberId,
        UUID companyId,
        Boolean isAnonymous,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public CompanyViewLogging(UUID memberId, UUID companyId) {
        this(null, memberId, companyId, false, null);
    }

    @Builder(builderClassName = "AnonymousBuilder", builderMethodName = "AnonymousBuilder")
    public CompanyViewLogging(UUID companyId) {
        this(null, null, companyId, true, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public CompanyViewLogging {
        Assert.notNull(companyId, "CompanyId must not be null");
        Assert.notNull(isAnonymous, "IsAnonymous must not be null");
    }
}