package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

import java.util.Objects;

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
        Objects.requireNonNull(reviewDetails, "ReviewDetails can not be null");
        Objects.requireNonNull(reviewGrades, "ReviewGrades can not be null");
        Objects.requireNonNull(authorId, "AuthorId can not be null");
        Objects.requireNonNull(companyId, "CompanyId can not be null");
    }

    public void isAuthor(final MemberId memberId) {
        authorId.isEqual(memberId);
    }
}