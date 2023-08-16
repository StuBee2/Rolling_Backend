package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class CompanyId extends BaseId {

    private CompanyId(UUID id) {
        super(id);
    }

    public static CompanyId create(final UUID id) {
        return new CompanyId(id);
    }

}