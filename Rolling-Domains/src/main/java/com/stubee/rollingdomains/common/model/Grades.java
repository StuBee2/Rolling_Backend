package com.stubee.rollingdomains.common.model;

import com.stubee.rollingdomains.common.exception.WrongCalculationException;
import lombok.Getter;

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
        Objects.requireNonNull(total, "Total Can not be null");
        Objects.requireNonNull(salaryAndBenefits, "SalaryAndBenefits Can not be null");
        Objects.requireNonNull(workLifeBalance, "WorkLifeBalance Can not be null");
        Objects.requireNonNull(organizationalCulture, "OrganizationalCulture Can not be null");
        Objects.requireNonNull(careerAdvancement, "CareerAdvancement Can not be null");

        this.salaryAndBenefits = salaryAndBenefits;
        this.workLifeBalance = workLifeBalance;
        this.organizationalCulture = organizationalCulture;
        this.careerAdvancement = careerAdvancement;

        isRightCalculation(total);

        this.total = total;
    }

    protected Grades(Double salaryAndBenefits, Double workLifeBalance,
                     Double organizationalCulture, Double careerAdvancement) {
        Objects.requireNonNull(salaryAndBenefits, "SalaryAndBenefits Can not be null");
        Objects.requireNonNull(workLifeBalance, "WorkLifeBalance Can not be null");
        Objects.requireNonNull(organizationalCulture, "OrganizationalCulture Can not be null");
        Objects.requireNonNull(careerAdvancement, "CareerAdvancement Can not be null");

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

}