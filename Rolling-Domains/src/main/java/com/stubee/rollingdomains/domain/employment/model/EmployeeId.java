package com.stubee.rollingdomains.domain.employment.model;

import com.stubee.rollingdomains.common.model.BaseId;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class EmployeeId extends BaseId {

    private EmployeeId(UUID id) {
        super(id);
    }

    public static EmployeeId create(final UUID id) {
        return new EmployeeId(id);
    }

}