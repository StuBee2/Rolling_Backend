package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.exception.NotMatchedMemberException;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

import java.util.Objects;

public record Review (
        ReviewId reviewId,
        ReviewDetails reviewDetails,
        Grades reviewGrades,
        AuthorId authorId,
        CompanyId companyId) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Review(ReviewDetails reviewDetails, Grades reviewGrades, AuthorId authorId, CompanyId companyId) {
        this(null, reviewDetails, reviewGrades, authorId, companyId);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Review {
        Objects.requireNonNull(reviewDetails);
        Objects.requireNonNull(reviewGrades);
        Objects.requireNonNull(authorId);
        Objects.requireNonNull(companyId);
    }

    public void isAuthor(final MemberId memberId) {
        if(!authorId.equals(memberId)) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}