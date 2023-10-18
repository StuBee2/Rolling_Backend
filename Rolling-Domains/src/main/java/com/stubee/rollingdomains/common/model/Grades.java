package com.stubee.rollingdomains.common.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.common.error.exception.WrongCalculationException;
import lombok.Getter;

@Getter
public abstract class Grades {

    private final Double total;
    private final Double salaryAndBenefits;
    private final Double workLifeBalance;
    private final Double organizationalCulture;
    private final Double careerAdvancement;

    protected Grades(Double total, Double salaryAndBenefits, Double workLifeBalance,
                     Double organizationalCulture, Double careerAdvancement) {
        Assert.notNull(total, "Total must not be null");
        assertIfObjectIsNull(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);

        this.salaryAndBenefits = salaryAndBenefits;
        this.workLifeBalance = workLifeBalance;
        this.organizationalCulture = organizationalCulture;
        this.careerAdvancement = careerAdvancement;

        isRightCalculation(total);

        this.total = total;
    }

    protected Grades(Double salaryAndBenefits, Double workLifeBalance,
                     Double organizationalCulture, Double careerAdvancement) {
        assertIfObjectIsNull(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);

        this.salaryAndBenefits = salaryAndBenefits;
        this.workLifeBalance = workLifeBalance;
        this.organizationalCulture = organizationalCulture;
        this.careerAdvancement = careerAdvancement;
        this.total = calculateTotal();
    }

    private void assertIfObjectIsNull(Double salaryAndBenefits, Double workLifeBalance,
                                      Double organizationalCulture, Double careerAdvancement) {
        Assert.notNull(salaryAndBenefits, "SalaryAndBenefits must not be null");
        Assert.notNull(workLifeBalance, "WorkLifeBalance must not be null");
        Assert.notNull(organizationalCulture, "OrganizationalCulture must not be null");
        Assert.notNull(careerAdvancement, "CareerAdvancement must not be null");
    }

    private void isRightCalculation(double total) {
        if(calculateTotal()!=total) {
            throw WrongCalculationException.EXCEPTION;
        }
    }

    private double calculateTotal() {
        return Math.round((salaryAndBenefits+workLifeBalance+organizationalCulture+careerAdvancement)/4.0*10)/10.0;
    }

}