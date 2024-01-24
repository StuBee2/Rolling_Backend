package rolling.domain.common.model;

import lombok.Getter;
import rolling.domain.common.error.exception.WrongCalculationException;

import java.util.Objects;

@Getter
public abstract class Grades {

    private final Double total;
    private final Double salaryAndBenefits;
    private final Double workLifeBalance;
    private final Double organizationalCulture;
    private final Double careerAdvancement;

    protected Grades(Double total, Double salaryAndBenefits, Double workLifeBalance,
                     Double organizationalCulture, Double careerAdvancement) {
        this.salaryAndBenefits = salaryAndBenefits;
        this.workLifeBalance = workLifeBalance;
        this.organizationalCulture = organizationalCulture;
        this.careerAdvancement = careerAdvancement;

        isRightCalculation(total);

        this.total = total;
    }

    protected Grades(Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
        this.salaryAndBenefits = salaryAndBenefits;
        this.workLifeBalance = workLifeBalance;
        this.organizationalCulture = organizationalCulture;
        this.careerAdvancement = careerAdvancement;
        this.total = calculateTotal();
    }

    private void isRightCalculation(double total) {
        if(calculateTotal()!=total) {
            throw WrongCalculationException.EXCEPTION;
        }
    }

    private double calculateTotal() {
        return Math.round((salaryAndBenefits+workLifeBalance+organizationalCulture+careerAdvancement)/4.0*10)/10.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grades grades = (Grades) o;
        return Objects.equals(total, grades.total)
                && Objects.equals(salaryAndBenefits, grades.salaryAndBenefits)
                && Objects.equals(workLifeBalance, grades.workLifeBalance)
                && Objects.equals(organizationalCulture, grades.organizationalCulture)
                && Objects.equals(careerAdvancement, grades.careerAdvancement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }
}