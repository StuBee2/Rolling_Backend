package com.stubee.companypersistence;

import com.stubee.rollingdomains.domain.company.model.*;

public abstract class CompanyTestUtils {

    public static Company create(String name, String description, Address address, CompanyGrades companyGrades) {
        return Company.ExceptIdBuilder()
                .companyDetails(CompanyDetails.ExceptDateBuilder()
                        .name(name)
                        .description(description)
                        .companyLogo(CompanyLogo.of(null, null))
                        .companyAddress(address)
                        .registrantId(RegistrantId.of(1L))
                        .build())
                .companyGrades(companyGrades)
                .build();
    }

}