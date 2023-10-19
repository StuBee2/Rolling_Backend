package com.stubee.persistencecommons.helper;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;
import static com.stubee.persistencecommons.entity.QStoryEntity.storyEntity;

public class ExpressionSupport {

    public static class Company {

        public static BooleanExpression isAccepted() {
            return companyEntity.companyStatus.eq(CompanyStatus.ACCEPTED);
        }

        public static BooleanExpression isEqualId(final Long id) {
            return companyEntity.id.eq(id);
        }

        public static BooleanExpression isEqualRegistrant(final Long registrantId) {
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

        public static BooleanExpression isEqualEmployee(final Long employeeId) {
            return employmentEntity.employeeId.eq(employeeId);
        }

        public static BooleanExpression isEqualEmployeeAndEmployer(final Long employeeId, final Long employerId) {
            return isEqualEmployee(employeeId).and(isEqualEmployer(employerId));
        }

        private static BooleanExpression isEqualEmployer(final Long employerId) {
            return employmentEntity.employerId.eq(employerId);
        }

    }

    public static class Story {

        public static BooleanExpression isEqualId(final Long id) {
            return storyEntity.id.eq(id);
        }

        public static BooleanExpression isEqualAuthor(final Long memberId) {
            return storyEntity.memberId.eq(memberId);
        }

        public static BooleanExpression isEqualCompany(final Long companyId) {
            return storyEntity.companyId.eq(companyId);
        }

    }

}