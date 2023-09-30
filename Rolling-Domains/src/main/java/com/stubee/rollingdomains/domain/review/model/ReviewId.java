package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.BaseId;

import java.util.UUID;

public class ReviewId extends BaseId {

    private ReviewId(UUID id) {
        super(id);
    }

    public static ReviewId of(final UUID id) {
        return new ReviewId(id);
    }

}