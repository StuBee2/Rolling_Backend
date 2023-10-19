package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class EmployerId extends BaseId {

    private EmployerId(Long id) {
        super(id);
    }

    public static EmployerId of(final Long id) {
        return new EmployerId(id);
    }

}