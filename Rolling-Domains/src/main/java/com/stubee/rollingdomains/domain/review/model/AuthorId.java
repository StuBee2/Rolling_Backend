package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.BaseId;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class AuthorId extends BaseId {

    private AuthorId(UUID id) {
        super(id);
    }

    public static AuthorId create(UUID id) {
        return new AuthorId(id);
    }

}