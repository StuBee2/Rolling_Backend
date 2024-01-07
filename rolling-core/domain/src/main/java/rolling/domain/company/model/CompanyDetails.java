package rolling.domain.company.model;

import lombok.Builder;
import rolling.domain.common.error.Assert;
import rolling.domain.company.consts.CompanyStatus;

import java.time.LocalDateTime;

public record CompanyDetails(
        RegistrantId registrantId,
        String name,
        String description,
        Address companyAddress,
        CompanyLogo companyLogo,
        CompanyStatus companyStatus,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    @Builder
    public CompanyDetails {
    }

    CompanyDetails cover(final CompanyDetails companyDetails) {
        Assert.notNull(companyDetails.name, "Name must not be null");
        Assert.notNull(companyDetails.companyAddress, "CompanyAddress must not be null");
        Assert.notNull(companyDetails.description, "Description must not be null");

        return new CompanyDetails(registrantId, companyDetails.name, companyDetails.description, companyDetails.companyAddress,
                companyDetails.companyLogo, companyStatus, createdAt, modifiedAt);
    }
}