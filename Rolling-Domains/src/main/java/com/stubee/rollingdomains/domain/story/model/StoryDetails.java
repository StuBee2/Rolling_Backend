package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import lombok.Builder;

import java.time.LocalDateTime;

public record StoryDetails(
        AuthorId authorId,
        CompanyId companyId,
        EmploymentDetails employmentDetails,
        CorporationDetails corporationDetails,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public StoryDetails(AuthorId authorId, CompanyId companyId, EmploymentDetails employmentDetails, CorporationDetails corporationDetails) {
        this(authorId, companyId, employmentDetails, corporationDetails, null, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public StoryDetails {
        Assert.notNull(authorId, "AuthorId must not be null");
        Assert.notNull(companyId, "CompanyId must not be null");
        Assert.notNull(employmentDetails, "EmploymentDetails must not be null");
        Assert.notNull(corporationDetails, "CorporationDetails must not be null");
    }

    public StoryDetails update(EmploymentDetails employmentDetails, CorporationDetails corporationDetails) {
        return new StoryDetails(authorId, companyId, employmentDetails, corporationDetails, createdAt, modifiedAt);
    }
}