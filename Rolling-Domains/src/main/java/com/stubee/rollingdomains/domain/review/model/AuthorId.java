package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class AuthorId extends BaseId {

    private AuthorId(UUID id) {
        super(id);
    }

    public static AuthorId of(BaseId id) {
        return new AuthorId(id.getId());
    }

    public static AuthorId of(UUID id) {
        return new AuthorId(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}