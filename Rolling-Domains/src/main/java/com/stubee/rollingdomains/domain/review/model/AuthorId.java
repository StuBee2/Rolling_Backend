package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class AuthorId extends BaseId {

    private AuthorId(UUID id) {
        super(id);
    }

    public static AuthorId create(UUID id) {
        return new AuthorId(id);
    }

}