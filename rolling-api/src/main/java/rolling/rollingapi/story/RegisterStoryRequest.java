package rolling.rollingapi.story;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import rolling.application.story.interactor.command.RegisterStoryCommand;

record RegisterStoryRequest(
        @NotNull Long companyId,
        @NotNull String position,
        String schoolLife,
        String preparationCourse,
        String employmentProcess,
        String interviewQuestion,
        String mostImportantThing,

        @NotNull String welfare,
        String commuteTime,
        String meal,
        @NotNull String pros,
        @NotNull String cons,
        String corporationEtc,

        @NotNull @Min(1) @Max(5) Short salaryAndBenefits,
        @NotNull @Min(1) @Max(5) Short workLifeBalance,
        @NotNull @Min(1) @Max(5) Short organizationalCulture,
        @NotNull @Min(1) @Max(5) Short careerAdvancement) {
    public RegisterStoryCommand toCommand() {
        return RegisterStoryCommand.of(
                companyId,
                position,
                schoolLife,
                preparationCourse,
                employmentProcess,
                interviewQuestion,
                mostImportantThing,
                welfare,
                commuteTime,
                meal,
                pros,
                cons,
                corporationEtc,

                salaryAndBenefits,
                workLifeBalance,
                organizationalCulture,
                careerAdvancement);
    }
}