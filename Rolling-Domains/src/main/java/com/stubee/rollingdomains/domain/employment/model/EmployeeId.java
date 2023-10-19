package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class EmployeeId extends BaseId {

    private EmployeeId(Long id) {
        super(id);
    }

    public static EmployeeId of(final Long id) {
        return new EmployeeId(id);
    }

    public static EmployeeId of(final BaseId id) {
        return new EmployeeId(id.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}