package rolling.domain.company.model;

import lombok.Builder;
import rolling.domain.common.model.Grades;

public final class CompanyGrades extends Grades {

    @Builder(builderClassName = "ExceptTotalBuilder", builderMethodName = "ExceptTotalBuilder")
    public CompanyGrades(Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    @Builder
    public CompanyGrades(Double total, Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    public static CompanyGrades zero() {
        return new CompanyGrades(0.0, 0.0, 0.0, 0.0);
    }

}