package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class RegistrantId extends BaseId {

    private RegistrantId(Long id) {
        super(id);
    }

    public static RegistrantId of(final Long id) {
        return new RegistrantId(id);
    }

    public static RegistrantId of(final BaseId id) {
        return new RegistrantId(id.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}