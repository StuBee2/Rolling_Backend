package rolling.domain.story.model;

import lombok.Builder;
import rolling.domain.common.model.Grades;

public final class ReviewGrades extends Grades {

    @Builder(builderClassName = "ExceptTotalBuilder", builderMethodName = "ExceptTotalBuilder")
    public ReviewGrades(Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    @Builder
    public ReviewGrades(Double total, Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

}