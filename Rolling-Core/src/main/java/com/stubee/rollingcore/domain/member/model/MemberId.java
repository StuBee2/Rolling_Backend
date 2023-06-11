package com.stubee.rollingcore.domain.member.model;

import java.util.Objects;
import java.util.UUID;

public record MemberId (
        UUID id) {
    public static MemberId create(final UUID id) {
        return new MemberId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemberId comparedId = (MemberId) obj;
        return Objects.equals(this.id, comparedId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}