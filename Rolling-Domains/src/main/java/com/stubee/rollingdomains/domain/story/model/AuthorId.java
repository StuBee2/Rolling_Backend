package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.model.BaseId;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public final class AuthorId extends MemberId {

    private AuthorId(final Long id) {
        super(id);
    }

    public static AuthorId of(final BaseId id) {
        return new AuthorId(id.getId());
    }

    public static AuthorId of(final Long id) {
        return new AuthorId(id);
    }

}