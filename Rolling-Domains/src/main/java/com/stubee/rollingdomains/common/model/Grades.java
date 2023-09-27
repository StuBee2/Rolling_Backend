package com.stubee.rollingdomains.common.model;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Grades(
        Double totalGrade,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement) {
    public static Grades create(final double salaryAndBenefits, final double workLifeBalance,
                                 final double organizationalCulture, final double careerAdvancement) {
        return Grades.builder()
                .totalGrade(calculateTotalGrade(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement))
                .salaryAndBenefits(salaryAndBenefits)
                .workLifeBalance(workLifeBalance)
                .organizationalCulture(organizationalCulture)
                .careerAdvancement(careerAdvancement)
                .build();
    }

    public static Grades createWithTotal(final double totalGrade, final double salaryAndBenefits, final double workLifeBalance,
                                          final double organizationalCulture, final double careerAdvancement) {
        return Grades.builder()
                .totalGrade(totalGrade)
                .salaryAndBenefits(salaryAndBenefits)
                .workLifeBalance(workLifeBalance)
                .organizationalCulture(organizationalCulture)
                .careerAdvancement(careerAdvancement)
                .build();
    }

    private static double calculateTotalGrade(final double salaryAndBenefits, final double workLifeBalance,
                                               final double organizationalCulture, final double careerAdvancement) {
        return Math.round((salaryAndBenefits+workLifeBalance+organizationalCulture+careerAdvancement)/4.0*10)/10.0;
    }
}