package rolling.domain.story.model;

import lombok.Builder;

public record EmploymentDetails(
        String schoolLife,
        String preparationCourse,
        String employmentProcess,
        String interviewQuestion,
        String mostImportantThing) {
    @Builder
    public EmploymentDetails {
    }
}