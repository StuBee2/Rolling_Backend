package rolling.domain.story.model;

import lombok.Builder;
import rolling.domain.common.error.Assert;
import rolling.domain.member.model.MemberId;

public final class Story {

    private final StoryId storyId;
    private StoryDetails storyDetails;
    private ReviewGrades reviewGrades;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Story(StoryDetails storyDetails, ReviewGrades reviewGrades) {
        this(null, storyDetails, reviewGrades);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Story(StoryId storyId, StoryDetails storyDetails, ReviewGrades reviewGrades) {
        Assert.notNull(storyDetails, "StoryDetails must not be null");
        Assert.notNull(reviewGrades, "ReviewGrades must not be null");

        this.storyId = storyId;
        this.storyDetails = storyDetails;
        this.reviewGrades = reviewGrades;
    }

    public void modify(final EmploymentDetails employmentDetails, final CorporationDetails corporationDetails,
                        final ReviewGrades reviewGrades, final MemberId memberId) {
        isAuthor(memberId);

        this.storyDetails = new StoryDetails(storyDetails.authorId(), storyDetails.companyId(), employmentDetails, corporationDetails,
                storyDetails.createdAt(), storyDetails.modifiedAt());
        this.reviewGrades = reviewGrades;
    }

    public void isAuthor(final MemberId memberId) {
        storyDetails.authorId().isEqual(memberId);
    }

    public StoryId storyId() {
        return storyId;
    }

    public StoryDetails storyDetails() {
        return storyDetails;
    }

    public ReviewGrades reviewGrades() {
        return reviewGrades;
    }

}