package com.stubee.rollingdomains.domain.company.model;

public record Address(
        String address) {
    public static Address of(final String address) {
        return new Address(address);
    }
}