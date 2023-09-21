package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class MemberId extends BaseId {

    private MemberId(UUID id) {
        super(id);
    }

    public static MemberId of(final UUID id) {
        return new MemberId(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}