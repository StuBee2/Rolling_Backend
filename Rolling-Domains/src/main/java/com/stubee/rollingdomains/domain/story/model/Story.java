package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

public record Story(
        StoryId reviewId,
        EmploymentDetails employmentDetails,
        CorporationDetails corporationDetails,
        ReviewGrades reviewGrades,
        StoryDetails storyDetails) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Story(EmploymentDetails employmentDetails, CorporationDetails corporationDetails, ReviewGrades reviewGrades,
                 StoryDetails storyDetails) {
        this(null, employmentDetails, corporationDetails, reviewGrades, storyDetails);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Story {
        Assert.notNull(employmentDetails, "EmploymentDetails must not be null");
        Assert.notNull(corporationDetails, "CorporationDetails must not be null");
        Assert.notNull(reviewGrades, "ReviewGrades must not be null");
        Assert.notNull(storyDetails, "StoryDetails must not be null");
    }

    public void isAuthor(final MemberId memberId) {
        storyDetails.authorId().isEqual(memberId);
    }
}