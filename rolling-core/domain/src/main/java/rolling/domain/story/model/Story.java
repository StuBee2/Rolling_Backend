package rolling.domain.story.model;

import lombok.Builder;
import rolling.domain.company.model.CompanyId;
import rolling.domain.member.model.MemberId;

import java.time.LocalDateTime;

public final class Story {

    private final StoryId id;
    private final AuthorId authorId;
    private final CompanyId companyId;
    private CorporationDetails corporationDetails;
    private EmploymentDetails employmentDetails;
    private ReviewGrades grades;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Story(final AuthorId authorId, final CompanyId companyId, final CorporationDetails corporationDetails,
                 final EmploymentDetails employmentDetails, final ReviewGrades reviewGrades) {
        this(null, authorId, companyId, corporationDetails, employmentDetails, reviewGrades, null, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Story(final StoryId id, final AuthorId authorId, final CompanyId companyId,
                 final CorporationDetails corporationDetails, final EmploymentDetails employmentDetails, final ReviewGrades grades,
                 final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this.id = id;
        this.authorId = authorId;
        this.companyId = companyId;
        this.corporationDetails = corporationDetails;
        this.employmentDetails = employmentDetails;
        this.grades = grades;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modify(final CorporationDetails corporationDetails, final EmploymentDetails employmentDetails,
                        final ReviewGrades reviewGrades, final MemberId memberId) {
        isAuthor(memberId);

        this.corporationDetails = corporationDetails;
        this.employmentDetails = employmentDetails;
        this.grades = reviewGrades;
    }

    public void isAuthor(final MemberId memberId) {
        authorId.isEqual(memberId);
    }

    public StoryId id() {
        return id;
    }

    public AuthorId authorId() {
        return authorId;
    }

    public CompanyId companyId() {
        return companyId;
    }

    public CorporationDetails corporationDetails() {
        return corporationDetails;
    }

    public EmploymentDetails employmentDetails() {
        return employmentDetails;
    }

    public ReviewGrades grades() {
        return grades;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

}