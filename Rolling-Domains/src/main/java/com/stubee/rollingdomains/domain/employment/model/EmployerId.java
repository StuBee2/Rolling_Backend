package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class EmployerId extends BaseId {

    private EmployerId(UUID id) {
        super(id);
    }

    public static EmployerId of(final UUID id) {
        return new EmployerId(id);
    }

}