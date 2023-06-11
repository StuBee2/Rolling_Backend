package com.stubee.rollingcore.common.model;

import lombok.Builder;

@Builder
public record Grades(
        Double totalGrade,
        Double balanceGrade,
        Double salaryGrade,
        Double welfareGrade) {
    public static Grades create(final double balanceGrade, final double salaryGrade, final double welfareGrade) {
        return Grades.builder()
                .totalGrade(calculateTotalGrade(balanceGrade, salaryGrade, welfareGrade))
                .balanceGrade(balanceGrade)
                .salaryGrade(salaryGrade)
                .welfareGrade(welfareGrade)
                .build();
    }

    public static Grades createWithTotal(final double totalGrade, final double balanceGrade, final double salaryGrade, final double welfareGrade) {
        return Grades.builder()
                .totalGrade(totalGrade)
                .balanceGrade(balanceGrade)
                .salaryGrade(salaryGrade)
                .welfareGrade(welfareGrade)
                .build();
    }

    private static double calculateTotalGrade(final double balanceGrade, final double salaryGrade, final double welfareGrade) {
        return Math.round((balanceGrade+salaryGrade+welfareGrade)/3.0*10)/10.0;
    }
}