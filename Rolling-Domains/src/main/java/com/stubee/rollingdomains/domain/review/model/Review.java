package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

public record Review (
        ReviewId reviewId,
        ReviewDetails reviewDetails,
        ReviewGrades reviewGrades,
        AuthorId authorId,
        CompanyId companyId) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Review(ReviewDetails reviewDetails, ReviewGrades reviewGrades, AuthorId authorId, CompanyId companyId) {
        this(null, reviewDetails, reviewGrades, authorId, companyId);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Review {
        Assert.notNull(reviewDetails, "ReviewDetails must not be null");
        Assert.notNull(reviewGrades, "ReviewGrades must not be null");
        Assert.notNull(authorId, "AuthorId must not be null");
        Assert.notNull(companyId, "CompanyId must not be null");
    }

    public void isAuthor(final MemberId memberId) {
        authorId.isEqual(memberId);
    }
}