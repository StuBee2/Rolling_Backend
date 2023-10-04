package com.stubee.rollingdomains.common.model;

import com.stubee.rollingdomains.common.exception.WrongCalculationException;
import lombok.Builder;

import java.util.Objects;

public record Grades(
        Double totalGrade,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement) {
    public static Grades zeroGrades() {
        return new Grades(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Builder(builderClassName = "ExceptTotalBuilder", builderMethodName = "ExceptTotalBuilder")
    public Grades(double salaryAndBenefits, double workLifeBalance, double organizationalCulture, double careerAdvancement) {
        this(calculateTotalGrade(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement),
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    @Builder(builderClassName = "WithTotalBuilder", builderMethodName = "WithTotalBuilder")
    public Grades {
        Objects.requireNonNull(totalGrade);
        Objects.requireNonNull(salaryAndBenefits);
        Objects.requireNonNull(workLifeBalance);
        Objects.requireNonNull(organizationalCulture);
        Objects.requireNonNull(careerAdvancement);
        isRightCalculation(totalGrade, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    private static double calculateTotalGrade(final double salaryAndBenefits, final double workLifeBalance,
                                               final double organizationalCulture, final double careerAdvancement) {
        return Math.round((salaryAndBenefits+workLifeBalance+organizationalCulture+careerAdvancement)/4.0*10)/10.0;
    }

    private void isRightCalculation(double totalGrade, double salaryAndBenefits, double workLifeBalance, double organizationalCulture, double careerAdvancement) {
        if(!(totalGrade==calculateTotalGrade(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement))) {
            throw WrongCalculationException.EXCEPTION;
        }
    }
}