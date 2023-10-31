package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

public record Story(
        StoryId storyId,
        StoryDetails storyDetails,
        ReviewGrades reviewGrades) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Story(StoryDetails storyDetails, ReviewGrades reviewGrades) {
        this(null, storyDetails, reviewGrades);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Story {
        Assert.notNull(storyDetails, "StoryDetails must not be null");
        Assert.notNull(reviewGrades, "ReviewGrades must not be null");
    }

    public Story update(final EmploymentDetails employmentDetails, final CorporationDetails corporationDetails,
                        final ReviewGrades reviewGrades) {
        return new Story(storyId, storyDetails.update(employmentDetails, corporationDetails), reviewGrades);
    }

    public void isAuthor(final MemberId memberId) {
        storyDetails.authorId().isEqual(memberId);
    }
}