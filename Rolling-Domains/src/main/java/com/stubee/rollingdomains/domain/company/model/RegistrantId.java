package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class RegistrantId extends BaseId {

    private RegistrantId(UUID id) {
        super(id);
    }

    public static RegistrantId create(final UUID id) {
        return new RegistrantId(id);
    }

}