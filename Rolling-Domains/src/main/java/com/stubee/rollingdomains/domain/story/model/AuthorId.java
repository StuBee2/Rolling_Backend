package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.model.BaseId;

public class AuthorId extends BaseId {

    private AuthorId(Long id) {
        super(id);
    }

    public static AuthorId of(BaseId id) {
        return new AuthorId(id.getId());
    }

    public static AuthorId of(Long id) {
        return new AuthorId(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}