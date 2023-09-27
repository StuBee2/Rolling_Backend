package com.stubee.persistencecommons.helper;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;

import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;
import static com.stubee.persistencecommons.entity.QReviewEntity.reviewEntity;

public class ExpressionSupport {

    public static class Company {

        public static BooleanExpression isAccepted() {
            return companyEntity.companyStatus.eq(CompanyStatus.ACCEPTED);
        }

        public static BooleanExpression isEqualId(final UUID id) {
            return companyEntity.id.eq(id);
        }

        public static BooleanExpression isEqualRegistrant(final UUID registrantId) {
            return companyEntity.registrantId.eq(registrantId).and(isAccepted());
        }

        public static BooleanExpression isContainingName(final String name) {
            return companyEntity.name.contains(name).and(isAccepted());
        }

        public static BooleanExpression isEqualName(final String name) {
            return companyEntity.name.eq(name).and(isAccepted());
        }

    }

    public static class Employment {

        public static BooleanExpression isEqualEmployee(final UUID employeeId) {
            return employmentEntity.employeeId.eq(employeeId);
        }

        public static BooleanExpression isEqualEmployeeAndEmployer(final UUID employeeId, final UUID employerId) {
            return isEqualEmployee(employeeId).and(isEqualEmployer(employerId));
        }

        private static BooleanExpression isEqualEmployer(final UUID employerId) {
            return employmentEntity.employerId.eq(employerId);
        }

    }

    public static class Review {

        public static BooleanExpression isEqualId(final UUID id) {
            return reviewEntity.id.eq(id);
        }

        public static BooleanExpression isEqualAuthor(final UUID memberId) {
            return reviewEntity.memberId.eq(memberId);
        }

        public static BooleanExpression isEqualCompany(final UUID companyId) {
            return reviewEntity.companyId.eq(companyId);
        }

    }

}