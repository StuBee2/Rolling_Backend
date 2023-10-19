package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class MemberId extends BaseId {

    private MemberId(Long id) {
        super(id);
    }

    public static MemberId of(final Long id) {
        return new MemberId(id);
    }

    public static MemberId of(final BaseId baseId) {
        return new MemberId(baseId.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}