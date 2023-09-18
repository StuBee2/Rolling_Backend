package com.stubee.rollingdomains.common.dtos.request;

public record PageRequest(
        Long page,
        Long size) {}