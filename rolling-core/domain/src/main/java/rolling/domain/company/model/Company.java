package rolling.domain.company.model;

import lombok.Builder;
import rolling.domain.company.consts.CompanyStatus;
import rolling.domain.company.exception.DuplicatedCompanyNameException;
import rolling.domain.company.service.CompanyService;
import rolling.domain.member.model.MemberId;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Company {

    private final CompanyId id;
    private final RegistrantId registrantId;
    private CompanyStatus status;
    private CompanyDetails details;
    private CompanyGrades grades;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Company(final RegistrantId registrantId, final CompanyDetails details, final CompanyGrades grades) {
        this(null, registrantId, CompanyStatus.ACCEPTED, details, grades, null, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Company(final CompanyId id, final RegistrantId registrantId, final CompanyStatus status, final CompanyDetails details,
                   final CompanyGrades grades, final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this.id = id;
        this.registrantId = registrantId;
        this.status = status;
        this.details = details;
        this.grades = grades;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modify(final MemberId memberId, final String name, final CompanyService companyService) {
        isRegistrant(memberId);

        if(!Objects.equals(this.details.name(), name)) {
            if(companyService.isNameDuplicate(name)) {
                throw DuplicatedCompanyNameException.EXCEPTION;
            }
        }

        this.details = new CompanyDetails(name, details.description(), details.address(), details.logo());
    }

    public void modify(final CompanyStatus status) {
        this.status = status;
    }

    public void modify(final CompanyGrades companyGrades) {
        this.grades = companyGrades;
    }

    private void isRegistrant(final MemberId memberId) {
        registrantId.isEqual(memberId);
    }

    public CompanyId id() {
        return id;
    }

    public RegistrantId registrantId() {
        return registrantId;
    }

    public CompanyStatus status() {
        return status;
    }

    public CompanyDetails details() {
        return details;
    }

    public CompanyGrades grades() {
        return grades;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

}