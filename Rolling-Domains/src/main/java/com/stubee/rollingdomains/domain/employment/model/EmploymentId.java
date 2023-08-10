package com.stubee.rollingdomains.domain.employment.model;

import java.util.Objects;

public record EmploymentId(
        Long id) {
    public static EmploymentId create(final Long id) {
        return new EmploymentId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmploymentId comparedId = (EmploymentId) obj;
        return Objects.equals(this.id, comparedId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}