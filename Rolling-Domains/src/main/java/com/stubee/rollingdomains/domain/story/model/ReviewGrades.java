package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.model.Grades;
import lombok.Builder;

public class ReviewGrades extends Grades {

    @Builder(builderClassName = "ExceptTotalBuilder", builderMethodName = "ExceptTotalBuilder")
    public ReviewGrades(Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    @Builder(builderClassName = "WithTotalBuilder", builderMethodName = "WithTotalBuilder")
    public ReviewGrades(Double total, Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

}