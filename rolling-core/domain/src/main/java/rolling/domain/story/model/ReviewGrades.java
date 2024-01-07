package rolling.domain.story.model;

import lombok.Builder;
import rolling.domain.common.model.Grades;

public final class ReviewGrades extends Grades {

    @Builder
    public ReviewGrades(Double total, Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

}