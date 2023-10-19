package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import lombok.Builder;

import java.time.LocalDateTime;

public record StoryDetails(
        AuthorId authorId,
        CompanyId companyId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public StoryDetails(AuthorId authorId, CompanyId companyId) {
        this(authorId, companyId, null, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public StoryDetails {
        Assert.notNull(authorId, "AuthorId must not be null");
        Assert.notNull(companyId, "CompanyId must not be null");
    }
}