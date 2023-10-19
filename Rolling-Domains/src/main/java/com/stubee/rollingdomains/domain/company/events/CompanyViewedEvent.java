package com.stubee.rollingdomains.domain.company.events;

public record CompanyViewedEvent(
        Long companyId) {
    public static CompanyViewedEvent of(Long companyId) {
        return new CompanyViewedEvent(companyId);
    }
}