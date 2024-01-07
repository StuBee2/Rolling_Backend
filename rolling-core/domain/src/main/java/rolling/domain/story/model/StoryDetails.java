package rolling.domain.story.model;

import lombok.Builder;
import rolling.domain.company.model.CompanyId;

import java.time.LocalDateTime;

public record StoryDetails(
        AuthorId authorId,
        CompanyId companyId,
        EmploymentDetails employmentDetails,
        CorporationDetails corporationDetails,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    @Builder
    public StoryDetails {
    }
}