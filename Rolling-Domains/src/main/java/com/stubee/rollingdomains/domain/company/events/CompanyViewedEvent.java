package com.stubee.rollingdomains.domain.company.events;

import java.util.UUID;

public record CompanyViewedEvent(
        UUID companyId) {
    public static CompanyViewedEvent of(UUID companyId) {
        return new CompanyViewedEvent(companyId);
    }
}