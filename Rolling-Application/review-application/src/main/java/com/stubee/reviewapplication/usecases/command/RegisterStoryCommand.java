package com.stubee.reviewapplication.usecases.command;

public record RegisterStoryCommand(
        Long companyId,
        String position,
        String schoolLife,
        String preparationCourse,
        String employmentProcess,
        String interviewQuestion,
        String mostImportantThing,

        String welfare,
        String commuteTime,
        String meal,
        String pros,
        String cons,
        String corporationEtc,

        Short salaryAndBenefits,
        Short workLifeBalance,
        Short organizationalCulture,
        Short careerAdvancement) {
    public static RegisterStoryCommand of(final Long companyId,
                                          final String position,
                                          final String schoolLife,
                                          final String preparationCourse,
                                          final String employmentProcess,
                                          final String interviewQuestion,
                                          final String mostImportantThing,

                                          final String welfare,
                                          final String commuteTime,
                                          final String meal,
                                          final String advantages,
                                          final String disAdvantages,
                                          final String corporationEtc,

                                          final Short salaryAndBenefits, final Short workLifeBalance,
                                          final Short organizationalCulture, final Short careerAdvancement) {
        return new RegisterStoryCommand(companyId,
                position, schoolLife, preparationCourse, employmentProcess, interviewQuestion, mostImportantThing,
                welfare, commuteTime, meal, advantages, disAdvantages, corporationEtc,
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }
}