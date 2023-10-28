package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.error.Assert;

public record Address(
        String address,
        String etc) {
    public static Address of(final String address, final String etc) {
        Assert.notNull(address, "Address must not be null");

        return new Address(address, etc);
    }
}