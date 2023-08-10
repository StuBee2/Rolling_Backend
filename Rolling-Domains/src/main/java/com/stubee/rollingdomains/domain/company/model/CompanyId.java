package com.stubee.rollingdomains.domain.company.model;

import java.util.Objects;
import java.util.UUID;

public record CompanyId (
        UUID id) {
    public static CompanyId create(final UUID id) {
        return new CompanyId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CompanyId comparedId = (CompanyId) obj;
        return Objects.equals(this.id, comparedId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}