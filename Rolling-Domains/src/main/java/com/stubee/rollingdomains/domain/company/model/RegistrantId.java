package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.BaseId;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public final class RegistrantId extends MemberId {

    private RegistrantId(final Long id) {
        super(id);
    }

    public static RegistrantId of(final Long id) {
        return new RegistrantId(id);
    }

    public static RegistrantId of(final BaseId id) {
        return new RegistrantId(id.getId());
    }

}