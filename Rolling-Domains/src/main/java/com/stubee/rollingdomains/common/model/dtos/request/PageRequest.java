package com.stubee.rollingdomains.common.model.dtos.request;

public record PageRequest(
        long page,
        long size) {
    public static PageRequest of(final long page, final long size) {
        return new PageRequest(page, size);
    }
}