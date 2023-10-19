package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.Grades;
import lombok.Builder;

public class CompanyGrades extends Grades {

    @Builder(builderClassName = "WithTotalBuilder", builderMethodName = "WithTotalBuilder")
    public CompanyGrades(Double total, Double salaryAndBenefits, Double workLifeBalance,
                         Double organizationalCulture, Double careerAdvancement) {
        super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    @Builder(builderClassName = "ExceptTotalBuilder", builderMethodName = "ExceptTotalBuilder")
    public CompanyGrades(Double salaryAndBenefits, Double workLifeBalance,
                         Double organizationalCulture, Double careerAdvancement) {
        super(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    public static CompanyGrades zero() {
        return new CompanyGrades(0.0, 0.0, 0.0, 0.0, 0.0);
    }

}