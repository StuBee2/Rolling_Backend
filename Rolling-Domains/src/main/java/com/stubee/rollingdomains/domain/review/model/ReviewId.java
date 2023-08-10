package com.stubee.rollingdomains.domain.review.model;

import java.util.Objects;
import java.util.UUID;

public record ReviewId (
        UUID id) {
    public static ReviewId create(final UUID id) {
        return new ReviewId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReviewId comparedId = (ReviewId) obj;
        return Objects.equals(this.id, comparedId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}