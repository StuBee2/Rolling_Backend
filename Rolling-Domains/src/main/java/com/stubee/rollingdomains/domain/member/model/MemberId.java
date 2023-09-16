package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.common.model.BaseId;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class MemberId extends BaseId {

    private MemberId(UUID id) {
        super(id);
    }

    public static MemberId create(final UUID id) {
        return new MemberId(id);
    }

}