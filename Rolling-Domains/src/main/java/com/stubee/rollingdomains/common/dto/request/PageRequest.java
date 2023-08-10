package com.stubee.rollingdomains.common.dto.request;

public record PageRequest(
        Long page,
        Long size) {}